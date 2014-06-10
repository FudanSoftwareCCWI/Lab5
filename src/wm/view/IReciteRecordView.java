package wm.view;

public interface IReciteRecordView {
	public void setCorrectPrecentageText(double percentage);
	public void setCorrectCountText(int currentCount) ;
	public void setIncorrectCountText(int incorrectCount);
	public void setNameText(String name);
	public void setRecitedSizeText(int recitedSize) ;
	public void showTablePanel();
}
