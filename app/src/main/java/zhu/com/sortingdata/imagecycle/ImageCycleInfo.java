package zhu.com.sortingdata.imagecycle;

/**
 * 轮播图片对象
 * @author Administrator
 *
 */
public class ImageCycleInfo {

	String id = "";// id
	Integer img = 0 ;// 本地图片id
	String content = "";// 内容
	String type = "";// 类型
	Object imgPath = "" ;// 图片路径

	public Object getImgPath() {
		return imgPath;
	}
	public void setImgPath(Object imgPath) {
		this.imgPath = imgPath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public Integer getImg() {
		return img;
	}
	public void setImg(Integer img) {
		this.img = img;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
