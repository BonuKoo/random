package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.extern.slf4j.Slf4j;

//액션팩토리 같은 기능이라고 생각하자.

//bean에 등록해두면 scan해서 등록해줌. 
@Controller
@RequestMapping("/sample/*")
@Slf4j
public class SampleController {

	//@RequestMapping("")
	@GetMapping("/")     //2. 이러면 GET일 때만 동작
	public void basic() {
		log.info("basic....");
	}
	
//	@RequestMapping(value="/list", method=RequestMethod.GET) //1. 이러면 GET 방식으로 올 때만 작동
//	@RequestMapping(value="/list", method=RequestMethod.POST) //1. 이러면 POST 방식으로 올 때만 작동
//  * 브라우저 통해서 할 땐 당연히 늘 GET 방식	
	
	@GetMapping("/list") //2.이러면 POST일 때만 동작 Get->Post
	public String list(String name,Model model) {
		log.info("list....{}", name);
		model.addAttribute("name",name);
		return "list";
	}
	
	@GetMapping("/ex1") //name, age에 해당하는 값이 없으면 고장남
	public void ex1(String name, int age) {
		log.info("name : {}",name);
		log.info("age  : {}",age);
		
	}
	
	@GetMapping("/ex2") //값을 넣어주지 않았을 때, 이렇게 하면 defaultValue가 기본 값이 됨
	public void ex2(@RequestParam(name="name", defaultValue="kim")String name,
			@RequestParam(name="age", defaultValue="3")int age) {
		log.info("name : {}",name);
		log.info("age  : {}",age);
	}

	@GetMapping("/ex3") //name, age에 해당하는 값이 없으면 고장남
//	1. public void ex3(String n, int a) {
	   public void ex3(@RequestParam("name")String n, @RequestParam("age")int a) {	
		log.info("name : {}",n);  // 1. "name : {}",n   원래는 name과 n이 일치하지 않으면 에러난다.
		log.info("age  : {}",a);   // 2. 
		
	}
	
//	@GetMapping("/ex4") //name, age에 해당하는 값이 없으면 고장남
//	public String ex4(SampleDTO dto) {
//		log.info("dto : {}", dto);
//	//SampleDTO 로 보내서 변환	
//		return "ex4";
//	}
	
	@GetMapping("/ex4") //name, age에 해당하는 값이 없으면 고장남
	public String ex4(SampleDTO dto,@ModelAttribute("page") int page) { //DTO에 PAGE가 없지만, MODELaTTRIBUTE 에 PAGE가 만들어져 있어서 값이 들어간당.
		log.info("dto : {}", dto); //ModelAttribute를 통해 view로 보낼 수 있다.
	//SampleDTO 로 보내서 변환	
		return "ex4";
	} //DB가 있다는 가정 하에, 이 값들을 DB에 보낸 후 VO에 맞게 담김
	  //그럼 MVC2 패턴 마냥 VIEW에서 값을 받거나 ~ 출력하거나 
	
	
	@InitBinder
	public void initBinder (WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new
				CustomDateEditor(dateFormat, false));
	}
	
	@GetMapping("/ex02List")
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		log.info("ids: " + ids);
		return "ex02List";
	}

	@GetMapping("/ex05")
	public String ex05(TodoDTO todo) {
		log.info("date : {}", todo);
		return "ex05";
	}
	
//	@GetMapping("/ex06")
//	public String ex06(LocalDate dueDate) {
//		log.info("date : {}", dueDate);
//		return "ex05";
//	}
	
	
	@GetMapping("/ex06")
	public String ex06(Model model) { //Model - 리퀘스트랑 비슷한 기능
		model.addAttribute("text", "Hello World");
		return "ex06"; //main - views - ex06을 찾아감
	}				  //servlet-context 파일안에 19번부터 21번 줄 사이로 작동
					

//	@GetMapping("/ex07") //입력하면 ex07이 list로 된다~
//	public String ex07() {
//		return "redirect:/sample/list";
//	}
	
	@GetMapping("/ex07") //입력하면 ex07이 list로 된다~
	public String ex07(RedirectAttributes rttr) {
		rttr.addAttribute("name", "AAA");
		rttr.addFlashAttribute("age", 10); //한번 받은 이후 새로고침하면 age = 10이 사라진다.
		return "redirect:/sample/list";
	}

	@GetMapping("/ex08")
	public void ex8() {
		log.info("/ex08"); //sample folder 안의 ex08

	
	}
	
	@GetMapping("/list/ex09")
	public void ex9() {
		log.info("/ex09");
	}

	@GetMapping("/ex10")
	public String ex10() {
		log.info("/ex10");
		return "/sample/ex10";
	}

	@GetMapping("/ex11")
	public SampleDTO ex11(Model model) {
		
		SampleDTO dto = new SampleDTO();
		dto.setName("park");
		dto.setAge(20);
		model.addAttribute("dto",dto);
		log.info("/ex11");
		return dto;
	}
							//JasonBind를 maven에 추가하면
							//Java 객체를 Jason 타입으로 바꿔준다.
	@GetMapping("/ex12")	//jackson-databind
	public @ResponseBody SampleDTO ex12() {
		
		SampleDTO dto = new SampleDTO();
		dto.setName("park");
		dto.setAge(20);
		log.info("/ex12");
		return dto;
	}
	
	@DeleteMapping("/list/{id}")
	//@GetMapping("/ex13/{id}")
	public String ex13(@PathVariable("id") String id) {
		log.info("id : {}",id);
		return "/sample/ex13";
	}
	//http://localhost:8082/sample/ex13/free
								//free 는 id 값에 들어간다.
	
	
}
//핸들러 맵핑
//  /            ==> HomeController 안에 있는 home 실행
//  /sample/     ==> SampleController 안의 basic 실행
//  /sample/list ==> SampleController 안의 list 실행
//  