package thinhtv.training.entity;

public class kessai_jyouhou {

	public kessai_jyouhou (String status,String ukeNo,String tokucyuNo,String jyuYo) {
		this.status = status;
		this.jyuYo = jyuYo;
		this.ukeNo =ukeNo;
		this.tokucyuNo = tokucyuNo;
	}
	/**ステータス*/
	private String status;
	/**受付NO*/
	private String ukeNo;
	/**特注NO*/
	private String tokucyuNo;
	/**需要先*/
	private String jyuYo;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUkeNo() {
		return ukeNo;
	}
	public void setUkeNo(String ukeNo) {
		this.ukeNo = ukeNo;
	}
	public String getTokucyuNo() {
		return tokucyuNo;
	}
	public void setTokucyuNo(String tokucyuNo) {
		this.tokucyuNo = tokucyuNo;
	}
	public String getJyuYo() {
		return jyuYo;
	}
	public void setJyuYo(String jyuYo) {
		this.jyuYo = jyuYo;
	}

}
