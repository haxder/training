package thinhtv.training.entity;

public class test {
	private kessai_jyouhou kessai_jyouhou;
	private kenToIrai kenToIrai;
	private jizenGaiKeizu jizenGaiKeizu;

	public kessai_jyouhou getKessai_jyouhou() {
		return kessai_jyouhou;
	}

	public void setKessai_jyouhou(kessai_jyouhou kessai_jyouhou) {
		this.kessai_jyouhou = kessai_jyouhou;
	}

	public kenToIrai getKenToIrai() {
		return kenToIrai;
	}

	public void setKenToIrai(kenToIrai kenToIrai) {
		this.kenToIrai = kenToIrai;
	}

	public jizenGaiKeizu getJizenGaiKeizu() {
		return jizenGaiKeizu;
	}

	public void setJizenGaiKeizu(jizenGaiKeizu jizenGaiKeizu) {
		this.jizenGaiKeizu = jizenGaiKeizu;
	}

	public test(kessai_jyouhou kessai_jyouhou,
			kenToIrai kenToIrai,
			jizenGaiKeizu jizenGaiKeizu) {
		this.kessai_jyouhou = kessai_jyouhou;
		this.kenToIrai = kenToIrai;
		this.jizenGaiKeizu = jizenGaiKeizu;
	}
}