package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	//필드
	
	//생성자

	//메소드-gs
	
	//메소드-일반
	//전화번호 삭제
	@RequestMapping(value="/delete/{no}/{id}", method={RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable("no") int num, @PathVariable("id") String id) {
		System.out.println("PhoneController>delete()");
		
		//주소에서 값 꺼내기
		System.out.println(num);
		System.out.println(id);
		
		//Dao로 처리하기(삭제)
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personDelete(num);
		System.out.println(count);
		
		return "redirect:/list";
	}
	
	
	
	
	@RequestMapping(value="/delete2", method={RequestMethod.GET, RequestMethod.POST})
	public String delete2(@RequestParam("no") int no) {
		System.out.println("PhoneController>delete()");
		
		//파라미터 꺼내기
		System.out.println(no);
		
		//Dao로 처리하기(삭제)
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personDelete(no);
		System.out.println(count);
		
		return "redirect:/list";
	}
	
	
	//전화번호 리스트
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhoneController>list()");
		
		//Dao를 통해서 personList(주소)을 가져온다
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();
		System.out.println(personList);
		
		//ds 데이터보내기 -->request attribute에 넣는다
		model.addAttribute("personList", personList);
		
		return "/WEB-INF/views/list.jsp";
		
	}
	
	
	//전화번호 등록
	@RequestMapping(value="/write", method={RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo) {
		
		System.out.println("PhoneController>write()");
		
		//파라미터 꺼내기+vo로 묶기를 DS해서 메소드의 파라미터로 보내준다

		//dao로 저장하기
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personInsert(personVo);
		System.out.println(count);
		
		
		//리다이렉트
		return "redirect:/list";
	}
	
	
	@RequestMapping(value="/write2", method={RequestMethod.GET, RequestMethod.POST})
	public String write2(@RequestParam("name") String name, 
					    @RequestParam("hp") String hp,
					    @RequestParam("company") String company) {
		
		System.out.println("PhoneController>write2()");
		
		//파라미터 꺼내기
		/*
		System.out.println(name);
		System.out.println(hp);
		System.out.println(company);
		*/
		
		//vo로 묶기
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);
		
		//dao로 저장하기
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personInsert(personVo);
		System.out.println(count);
		
		//리다이렉트
		return "redirect:/list";
	}
	
	
	
	//전화번호 등록 폼
	@RequestMapping(value="/writeForm", method={RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("PhoneController>writeForm()");
		
		return "/WEB-INF/views/writeForm.jsp";
	}
	
	
	
	//테스트
	@RequestMapping(value="/test", method= {RequestMethod.GET, RequestMethod.POST})
	public String test() {
		System.out.println("PhoneController>test()");
		//다오 
		return "/WEB-INF/views/test.jsp";
	}

	
	
	
	//등록메소드
	//수정폼메소드
	//삭제메소드
	//리스트메소드
	
}
