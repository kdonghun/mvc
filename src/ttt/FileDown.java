package ttt;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Action;
import model.ActionData;

public class FileDown implements Action {

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String fileName = request.getParameter("file");
		
		System.out.println(fileName);
		
		String path = request.getRealPath("up");
		path = "F:\\kim\\mvc\\mvcboard\\WebContent\\up";
		
		try {
			String en = URLEncoder.encode(fileName,"utf-8");
			
			response.setHeader("Content-Disposition", "attachment;fileName="+en);
			
			ServletOutputStream sos = response.getOutputStream();
			
			FileInputStream fis = new FileInputStream(path+"\\"+fileName);
			
			byte[] buf = new byte [1024];
			
			while(fis.available()>0) {
				int len = fis.read(buf);
				sos.write(buf,0,len);
			}
			
			sos.close();
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
