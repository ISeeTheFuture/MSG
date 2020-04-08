package com.kh.msg.edoc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.msg.common.util.Utils;
import com.kh.msg.edoc.model.service.EdocService;
import com.kh.msg.edoc.model.vo.EdocAtt;
import com.kh.msg.edoc.model.vo.EdocFlow;
import com.kh.msg.edoc.model.vo.EdocLeaveLtt;
import com.kh.msg.edoc.model.vo.EdocSrch;
import com.kh.msg.edoc.model.vo.Jstree;
import com.kh.msg.edoc.model.vo.JstreeMem;
import com.kh.msg.member.model.vo.Member;
import com.kh.msg.member.model.vo.OrgChart;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Slf4j
@RequestMapping("/edoc")
public class EdocController {

	@Autowired
	EdocService edocService;

	@GetMapping("/list.do")
	public ModelAndView list(@RequestParam(value="cPage", defaultValue="1") int cPage, String srchWord, @RequestParam(value="srchType", defaultValue="all")String srchType, @RequestParam(value="arrayDocuCheck", defaultValue="myDocu,reqDocu,compDocu,refDocu")String[] arrayDocuCheck, HttpSession session) {
		log.debug("=========내 전자문서 페이지=========");
		ModelAndView mav = new ModelAndView();
		final int numPerPage = 15;
		
		OrgChart m = (OrgChart)session.getAttribute("memberLoggedIn");// 로그인 객체 호출
		
		//일단 초기값은 모든 종류의 문서를 가져오는 것으로 가정
		String myDocu = "y"; 
		String reqDocu = "y"; 
		String compDocu = "y"; 
		String refDocu = "y";
		
		if(!Arrays.stream(arrayDocuCheck).anyMatch("myDocu"::equals)) myDocu="n";
		if(!Arrays.stream(arrayDocuCheck).anyMatch("reqDocu"::equals)) reqDocu="n";
		if(!Arrays.stream(arrayDocuCheck).anyMatch("compDocu"::equals)) compDocu="n";
		if(!Arrays.stream(arrayDocuCheck).anyMatch("refDocu"::equals)) refDocu="n";
		
		// docuCheckMap : 페이지 리다이렉트시 이전에 선택한 검색 조건을 유지 하기 위해 사용
		Map<String, String> docuCheckMap = new HashMap<String, String>();
		docuCheckMap.put("myDocu", myDocu);
		docuCheckMap.put("reqDocu", reqDocu);
		docuCheckMap.put("compDocu", compDocu);
		docuCheckMap.put("refDocu", refDocu);
		
		//
		Map<String, String> map = new HashMap<>();
		map.put("srchWord", srchWord);
		map.put("srchType", srchType);
		map.put("empNo", m.getEmpNo()+"");//member.getEmpNo()+""
		map.put("myDocu", myDocu);
		map.put("reqDocu", reqDocu);
		map.put("compDocu", compDocu);
		map.put("refDocu", refDocu);

		List<EdocSrch> list = edocService.selectMyList(cPage, numPerPage, map);
		
		log.debug("list.toString()={}", list.toString());
		int totalContents = edocService.selectMyEdocTotalContents(map);
		
		mav.addObject("myEdocList", list);
		
		
//		여기서부터 페이징
		final int totalPage = (int)(Math.ceil((double)totalContents/numPerPage));
		final int pageBarSize = 5;
		final int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
		final int pageEnd = (pageStart+pageBarSize)-1;
		int pageNo = pageStart;
		
		String pageBar = "";
		if(pageNo == 1) {
			pageBar += "<a href=\"#\" class=\"arrow\">&laquo;</a>";
		}
		else {
			pageBar += "<a href='/msg/edoc/list.do?cPage="+(pageNo-1)+"&srchWord="+srchWord+"&srchType="+srchType+"'>&laquo;</a>";
		}
		
		while(pageNo <= pageEnd  && pageNo <= totalPage) {
			if(pageNo == cPage) {
				pageBar += "<a class='active'>"+pageNo+"</a>";
			}
			else {
				pageBar += "<a href='/msg/edoc/list.do?cPage="+pageNo+"&srchWord="+srchWord+"&srchType="+srchType+"'>"+pageNo+"</a>";
			}
			pageNo++;
		};
		if(pageNo > totalPage) {
			pageBar += "<a href=\"#\" class=\"arrow\">&raquo;</a>";
		}
		else {
			pageBar += "<a href='/msg/edoc/list.do?cPage="+pageNo+"&srchWord="+srchWord+"&srchType="+srchType+"'>&raquo;</a>";
		};
		
		mav.addObject("pageBar", pageBar);
		mav.addObject("cPage", cPage);
		mav.addObject("docuCheckMap", docuCheckMap);
		mav.addObject("srchWord", srchWord);
		mav.addObject("srchType", srchType);
		
		
		mav.setViewName("edoc/edocList");
		
		return mav;
	}

	@GetMapping("/srch.do")
	public ModelAndView srch(@RequestParam(value="cPage", defaultValue="1") int cPage, String srchWord, @RequestParam(value="srchType", defaultValue="all")String srchType) {
		log.debug("=========전자문서 검색 페이지=========");
		ModelAndView mav = new ModelAndView();
		final int numPerPage = 15;
		
		log.debug("srchWord@srch.EdocController{}",srchWord);
		log.debug("srchType@srch.EdocController{}",srchType);
		
		List<EdocSrch> list = edocService.selectList(cPage, numPerPage, srchWord, srchType);
		
		int totalContents = edocService.selectEdocTotalContents(srchWord, srchType);
		
		
		mav.addObject("srchList", list);
		mav.addObject("totalContents", totalContents);
		
//		여기서부터 페이징
		final int totalPage = (int)(Math.ceil((double)totalContents/numPerPage));
		final int pageBarSize = 5;
		final int pageStart = ((cPage-1)/pageBarSize)*pageBarSize+1;
		final int pageEnd = (pageStart+pageBarSize)-1;
		int pageNo = pageStart;
		

		String pageBar = "";
		if(pageNo == 1) {
			pageBar += "<a href=\"#\" class=\"arrow\">&laquo;</a>";
		}
		else {
			pageBar += "<a href='/msg/edoc/srch.do?cPage="+(pageNo-1)+"&srchWord="+srchWord+"&srchType="+srchType+"'>&laquo;</a>";
		}
		
		while(pageNo <= pageEnd  && pageNo <= totalPage) {
			if(pageNo == cPage) {
				pageBar += "<a class='active'>"+pageNo+"</a>";
			}
			else {
				pageBar += "<a href='/msg/edoc/srch.do?cPage="+pageNo+"&srchWord="+srchWord+"&srchType="+srchType+"'>"+pageNo+"</a>";
			}
			pageNo++;
		};
		if(pageNo > totalPage) {
			pageBar += "<a href=\"#\" class=\"arrow\">&raquo;</a>";
		}
		else {
			pageBar += "<a href='/msg/edoc/srch.do?cPage="+pageNo+"&srchWord="+srchWord+"&srchType="+srchType+"'>&raquo;</a>";
		};

		mav.addObject("pageBar", pageBar);
		mav.addObject("cPage", cPage);
		
		
		mav.setViewName("edoc/edocSrch");
		
		return mav;
	}
	@GetMapping("/confirm.do")
	public String confirm() {
		return "edoc/edocConfirm";
	}

	@GetMapping("/read.do")
	public String read() {
		return "edoc/edocRead";
	}

	@GetMapping("/write.do")
	public String write(HttpSession session) {
		Member m = (Member)session.getAttribute("memberLoggedIn");
		log.debug("------------------------------------------------------------------{}",m);
		return "edoc/edocWrite";
	}
	
	@ResponseBody
	@PostMapping("/edocAtt.do")
	public Model edocAtt(@RequestParam(value="formData", required=false) MultipartFile[] formData, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception, IOException {
		log.debug("upFiles@EdocController{} = ", formData);
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String contextRootPath = request.getServletContext().getRealPath("/"); 
		DiskFileItemFactory diskFactory = new DiskFileItemFactory();
		diskFactory.setSizeThreshold(4096); //업로드시 사용할 임시 메모리
        diskFactory.setRepository(new File(contextRootPath + "/resources/upload/tmp"));
		ServletFileUpload upload = new ServletFileUpload(diskFactory);
        
        
        List<FileItem> items = upload.parseRequest(request); 
        Iterator iter = items.iterator(); //반복자(Iterator)로 받기​            
        while(iter.hasNext()) { //반목문으로 처리​    
            FileItem item = (FileItem) iter.next(); //아이템 얻기
             //4. FileItem이 폼 입력 항목인지 여부에 따라 알맞은 처리
            if(item.isFormField()){ //파일이 아닌경우
                processFormField(out, item);
            } else { //파일인 경우
                processUploadFile(out, item, contextRootPath);
            }
        }
        
        
		/*
		 * List<EdocAtt> edocAttList = new ArrayList<>(); for(MultipartFile f :
		 * formData) { System.out.println("11111111111111111111"); if(f.isEmpty())
		 * continue; System.out.println("222222222222222222222"); String originFilename=
		 * f.getOriginalFilename(); String renamedFilename =
		 * Utils.getRefile(originFilename);
		 * System.out.println("3333333333333333333333"); String saveDirectory =
		 * request.getServletContext().getRealPath("/resources/upload/edoc");
		 * f.transferTo(new File(saveDirectory, renamedFilename));
		 * System.out.println("4444444444444444444444"); EdocAtt edocAtt = new
		 * EdocAtt(); edocAtt.setOriginFilename(originFilename);
		 * edocAtt.setRenamedFilename(renamedFilename); edocAttList.add(edocAtt); }
		 */
//		log.debug("edocAttList@EdocController{} = ", edocAttList.toString());
//		model.addAttribute("edocAttList", edocAttList);
		
		return model;
	}
	
	
	
	@ResponseBody
	@PostMapping("/write.do")
	public String edocWrite(String empNo, String secuCd, String prsvCd, String edocTitle, String vctnCd, String startDt,
			String endDt, String leaveAmt, String leavePurpose, String leaveContact, String typeCd, String surEmpNo, String[] flowLine, String flowCd, @RequestParam(value="upFiles", required=false) MultipartFile[] upFiles, HttpServletRequest request) throws Exception, IOException {
		
		
		//새로운 EdocId 받아오기
		String edocId = edocService.newEdocId();
		
		List<EdocFlow> edocFlowList = new ArrayList<>();
		List<EdocAtt> edocAttList = new ArrayList<>();
		EdocLeaveLtt edocLeaveLtt = new EdocLeaveLtt();
		
		
		// 전자문서 등록
		edocLeaveLtt.setEdocId(edocId);
		edocLeaveLtt.setSecuCd(secuCd);
		edocLeaveLtt.setPrsvCd(prsvCd);
		edocLeaveLtt.setEmpNo(Integer.parseInt(empNo));
		edocLeaveLtt.setEdocTitle(edocTitle);
		edocLeaveLtt.setVctnCd(vctnCd);
		edocLeaveLtt.setStartDt(startDt);
		edocLeaveLtt.setEndDt(endDt);
		edocLeaveLtt.setLeaveAmt(Integer.parseInt(leaveAmt));
		edocLeaveLtt.setLeavePurpose(leavePurpose);
		edocLeaveLtt.setLeaveContact(leaveContact);
		if(surEmpNo != null) {
			edocLeaveLtt.setSurEmpNo(Integer.parseInt(surEmpNo));
		}
		edocLeaveLtt.setTypeCd(typeCd);

		// 결재선 등록
		if(flowLine.length > 1) {
			for(int i = 0; i < flowLine.length-1; i++) { // 더미 flowLine을 하나 추가했으므로, 컨트롤러로 받은 시점에서 하나를 덜 세는 것.
				EdocFlow ef = new EdocFlow();
				ef.setEdocId(edocId);
				// F1 : 결재, F2 : 전결
				if(flowCd != "") {
					if((Integer.parseInt(flowCd)-1)==i) ef.setFlowCd("F2");
				}
				else ef.setFlowCd("F1");
				ef.setFlowEmpNo(Integer.parseInt(flowLine[i].substring(0, 1)));
				ef.setFlowOrd(i+1);
				edocFlowList.add(ef);
			}
		}
		
		// 첨부파일 등록
		for(MultipartFile f : upFiles) {
			if(f.isEmpty()) continue;
			
			String originFilename= f.getOriginalFilename();
			String renamedFilename = Utils.getRefile(originFilename);
			
			String saveDirectory = request.getServletContext().getRealPath("/resources/upload/edoc");
			
			f.transferTo(new File(saveDirectory, renamedFilename));
			
			EdocAtt edocAtt = new EdocAtt();
			edocAtt.setOriginFilename(originFilename);
			edocAtt.setRenamedFilename(renamedFilename);
			edocAtt.setEdocId(edocId);
			edocAttList.add(edocAtt);
		}
		
		int result = edocService.edocWrite(edocLeaveLtt, edocAttList, edocFlowList);

		return "edoc/list.do";
	}


	@GetMapping("/jstree.do")
	public void jstree(HttpServletResponse response) {

		try {
			List<Jstree> list = edocService.selectJstree();
			log.debug("list@EdocController"+list.toString());
			
			JSONArray jsonArr = new JSONArray();
			
			for(int i = 0; i < list.size(); i++) {
				JSONObject sObject = new JSONObject(); 
				sObject.put("id", list.get(i).getId());
				if((list.get(i).getParent())==null) {
					sObject.put("parent", "#");
				} else {
					sObject.put("parent", list.get(i).getParent());
				}
				sObject.put("text", list.get(i).getText());
				sObject.put("icon", list.get(i).getIcon());
				jsonArr.add(sObject);
			}
			
			response.setCharacterEncoding("UTF-8");
			PrintWriter out;
			out = response.getWriter();
			out.write(jsonArr.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@GetMapping("/jstreeMem.do")
	public void jstreeMem(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("id").charAt(0) == 'D') {
			try {
				JSONObject sObject = new JSONObject(); 
				sObject.put("empNo", "fail");
				sObject.put("dept", "fail");
				sObject.put("job", "fail");
				sObject.put("name", "fail");
				
				response.setCharacterEncoding("UTF-8");
				PrintWriter out;
				out = response.getWriter();
				out.write(sObject.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				JstreeMem memOne = edocService.selectJstreeMem(request.getParameter("id"));
				
				log.debug("memOne@EdocController"+memOne.toString());
				
				JSONObject sObject = new JSONObject(); 
				sObject.put("empNo", memOne.getEmpNo());
				sObject.put("dept", memOne.getDeptName());
				sObject.put("job", memOne.getJobName());
				sObject.put("name", memOne.getEmpName());
				
				response.setCharacterEncoding("UTF-8");
				PrintWriter out;
				out = response.getWriter();
				out.write(sObject.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	
	@PostMapping("/pdfView.do")
	public void pdfView(HttpServletRequest request, HttpServletResponse response) throws InvalidFormatException, IOException {
		
		createDocFromTemplate(request);
		
	}


	private static void createDocFromTemplate(HttpServletRequest request) throws InvalidFormatException, IOException {
		// doc 템플릿 열 때에 읽기 쓰기 권한 획득
		
		XWPFDocument doc = new XWPFDocument(OPCPackage.open("template/leaveTemplate.docx"));
		
		// doc 템플릿에 DB값 삽입
		for (XWPFParagraph p : doc.getParagraphs()) {
			List<XWPFRun> runs = p.getRuns();
			if (runs != null) {
				for (XWPFRun r : runs) {
					String text = r.getText(0);
					if (text != null && text.contains("tmp")) {
						text = text.replace("tmp", "임시");
						r.setText(text, 0);
					}
				}
			}
		}
		for (XWPFTable tbl : doc.getTables()) {
			for (XWPFTableRow row : tbl.getRows()) {
				for (XWPFTableCell cell : row.getTableCells()) {
					for (XWPFParagraph p : cell.getParagraphs()) {
						for (XWPFRun r : p.getRuns()) {
							String text = r.getText(0);
							if (text != null && text.contains("tmp")) {
								text = text.replace("tmp", "임시");
								r.setText(text, 0);
							}
						}
					}
				}
			}
		}
		write2Pdf(doc, request);
		doc.close();
	}

	private static void write2Pdf(XWPFDocument doc, HttpServletRequest request) throws IOException{

		String optFolder = request.getServletContext().getRealPath("/resources/upload/edocPdf/");
		String optFileName = "createFileFromTemplate.pdf";

		File f = new File(optFolder);
		if(!f.exists()) {
			System.out.println("Created folder " + optFolder);
			f.mkdirs();
		}

		OutputStream out = new FileOutputStream(new File(optFolder+optFileName));
		PdfOptions options = PdfOptions.create().fontEncoding("Identity-H"); // 왜...Identity-H? 이거 설정안하거나 UTF-8 이런거 주면 한글 안보임
		PdfConverter.getInstance().convert(doc, out, options);


	}
    private void processFormField(PrintWriter out, FileItem item) 
            throws Exception{
            String name = item.getFieldName(); //필드명 얻기
            String value = item.getString("UTF-8"); //UTF-8형식으로 필드에 대한 값읽기
            
            out.println(name + ":" + value + "<BR>"); //출력
    }
    
    private void processUploadFile(PrintWriter out, FileItem item, String contextRootPath) throws Exception {
        String name = item.getFieldName(); //파일의 필드 이름 얻기
        String fileName = item.getName(); //파일명 얻기
        String contentType = item.getContentType();//컨텐츠 타입 얻기
        long fileSize = item.getSize(); //파일의 크기 얻기
        
        //업로드 파일명을 현재시간으로 변경후 저장
        String fileExt = fileName.substring(fileName.lastIndexOf("."));
        String uploadedFileName = System.currentTimeMillis() + fileExt; 
        System.out.println(fileExt);
        System.out.println(uploadedFileName);
        
        //저장할 절대 경로로 파일 객체 생성
        File uploadedFile = new File(contextRootPath + "/resources/upload/edoc" + uploadedFileName);
        item.write(uploadedFile); //파일 저장
        
        //========== 뷰단에 출력 =========//
        out.println("<P>");
        out.println("파라미터 이름:" + name + "<BR>");
        out.println("파일 이름:" + fileName + "<BR>");
        out.println("콘텐츠 타입:" + contentType + "<BR>");
        out.println("파일 사이즈:" + fileSize + "<BR>");
        out.println("실제저장경로 : "+uploadedFile.getPath()+"<BR>");
    }
    
}
