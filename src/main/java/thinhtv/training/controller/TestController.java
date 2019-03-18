package thinhtv.training.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import thinhtv.training.entity.History;


@ManagedBean(name= "testController")
@ViewScoped
public class TestController {
	private List<String> 分類 = getlist分類();
	private List<String> 特急サイン = getlist特急サイン();
	private String select特急サイン;
	private String select分類;
	private List<History> historyList;
	@PostConstruct
	private void init() {
		select特急サイン = "通常";
		select分類 = "分類1";
	}
	
	private List<String> getlist分類() {
		List<String> temp = new ArrayList<String>();
		temp.add("未選択");
		temp.add("分類1");
		temp.add("分類2");
		temp.add("分類3");
		temp.add("分類4");
		return temp;
	}
	private List<String> getlist特急サイン() {
		List<String> temp = new ArrayList<String>();
		temp.add("特急");
		temp.add("通常");
		return temp;
	}

	public List<String> get分類() {
		return 分類;
	}

	public String getSelect分類() {
		return select分類;
	}

	public void setSelect分類(String select分類) {
		this.select分類 = select分類;
	}

	public void set分類(List<String> 分類) {
		this.分類 = 分類;
	}
	public List<String> get特急サイン() {
		return 特急サイン;
	}
	public void set特急サイン(List<String> 特急サイン) {
		this.特急サイン = 特急サイン;
	}
	public String getSelect特急サイン() {
		return select特急サイン;
	}
	public void setSelect特急サイン(String select特急サイン) {
		this.select特急サイン = select特急サイン;
	}

	public List<History> getHistoryList() {
		List<History> hts = new ArrayList<History>();
		hts.add(new History(new Date(), "一時保存", "横河太郎", "ＸＸＸＸＸＸＸＸＸＸ", "Taro.Yokokawa＠xxxxxxx.coｍ", ""));
		return hts;
	}

}
