package demo.Controller;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

import demo.Repository.MountainRepository;
import demo.models.Mountain;
import lombok.extern.slf4j.Slf4j;

@Controller
@Transactional
@ResponseBody
@Slf4j
public class MountainController { 
	@Autowired
	MountainRepository mr;
	//전체 유저의 정보를 출력
	@RequestMapping("/mountain")
	public Iterable<Mountain> list_m(){
		return mr.findAll();
	}


	@RequestMapping(value = "/msearch/{name}",method=RequestMethod.GET)
	public Collection<Mountain> msearch(@PathVariable String name){
		return mr.findByNameLike("%"+name+"%");
	}
	
	@RequestMapping(value = "/mclassify/{city}",method=RequestMethod.GET)
	public Collection<Mountain> mclassify(@PathVariable String city){
		return mr.findByCityLike("%"+city+"%");
	}
	
}
