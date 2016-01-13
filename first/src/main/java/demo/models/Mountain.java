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
public class Mountain {
	@Id
	@GeneratedValue
	int idx;
	String name;
	String city;
	String addr;
	double gpsLatitude;
	double gpsLongitude;
	int height;
	String introduce;
	@Column(columnDefinition = "TEXT")
	String page;
	public List<String> getImages() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			String url = ForestApplication.HOST + "/img/mountain/" + idx + "/" + i + ".jpg";
			list.add(url);
		}
		return list;
	}
}