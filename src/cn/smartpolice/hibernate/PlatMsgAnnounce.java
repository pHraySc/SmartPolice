package cn.smartpolice.hibernate;

public class PlatMsgAnnounce {
	private String Title;
	private String Type;
	private String AnounceOb;
	private String Content;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public String getAnounceOb() {
		return AnounceOb;
	}
	public void setAnounceOb(String anounceOb) {
		AnounceOb = anounceOb;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	@Override
	public String toString() {
		return "PlatRunInfo [Title=" + Title + ", Type=" + Type
				+ ", AnounceOb=" + AnounceOb + ", Content=" + Content + "]";
	}
}
