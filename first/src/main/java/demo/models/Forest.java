package demo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import demo.ForestApplication;
import lombok.Data;

@Entity
@Data
public class Forest { 
	@Id
	@GeneratedValue
	int idx;
	String name;
	String addr;
	String newaddr;
	String telephone;
	String city;
	double gpsLatitude;
	double gpsLongitude;
	@Column(columnDefinition = "TEXT")
	String introduce;
	String page;
	public List<String> getImages() {
		List<String> list = new ArrayList<String>();
		for (int i = 1; i < 6; i++) {
			String url = ForestApplication.HOST + "/img/forest/" + idx + "/(" + i + ").jpg";
			list.add(url);
		}
		return list;
	}
}
