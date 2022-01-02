package Menu;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MenuFileManager {
	private File f;
	private FileReader fr;
	private BufferedReader br;
	
	public MenuFileManager() {
		f = null;
		fr = null;
		br = null;
	}
	
	// 선택한 파일의 count 리턴
	public int getFileCountInfo(String fileName) {
		String l = null;
		int count = 0;
		f = new File(fileName);
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			
			while((l=br.readLine())!=null) {
				count++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	// 선택한 파일의 품목 정보 리턴
	public String getSelectedFileInfo(String fileName, int listCount) {
		String l = null;
		int count = 0;
		f = new File(fileName);
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			
			while((l=br.readLine())!=null) {
				// 자료 데이터의 0번째 (품목번호)와 일치하는지 여부. 일치하면 일치한 품목 정보 리턴.
				if(count == listCount)
					break;
				count++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		return l;
	}
}
