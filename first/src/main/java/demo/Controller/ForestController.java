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

import demo.Repository.ForestRepository;
import demo.Repository.MountainRepository;
import demo.models.Forest;
import demo.models.Mountain;
import lombok.extern.slf4j.Slf4j;

@Controller
@Transactional
@ResponseBody
@Slf4j
public class ForestController { 
	@Autowired
	ForestRepository fr;
	//전체 유저의 정보를 출력
	@RequestMapping("/")
	public Iterable<Forest> list_f(){
	return fr.findAll();
	}
	@RequestMapping(value ="/save",method=RequestMethod.POST)
	public Forest add(@RequestParam String name, @RequestParam String addr
			,@RequestParam String newaddr,@RequestParam String telephone,@RequestParam String city){
		Forest forest = new Forest();
		forest.setName(name);
		forest.setAddr(addr);
		forest.setNewaddr(newaddr);
		forest.setTelephone(telephone);
		forest.setCity(city);
		fr.save(forest);
		return forest;
	}
	
	@RequestMapping(value = "/fsearch/{name}",method=RequestMethod.GET)
	public Collection<Forest> search(@PathVariable String name){
		return fr.findByNameLike("%"+name+"%");
	}
	
	@RequestMapping(value = "/fclassify/{city}",method=RequestMethod.GET)
	public Collection<Forest> fclassify(@PathVariable String city){
		return fr.findByCityLike("%"+city+"%");
	}	
}
