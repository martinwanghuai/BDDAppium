package utility;

import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IOUtils {

	public static String toUTF8String(final String urlStr){

		String result = null;
		try {
			if(!Checker.isBlank(urlStr)){
				String data = urlStr.toString();
				data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
				data = data.replaceAll("\\+", "%2B");
				result = URLDecoder.decode(data, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		
		return result;
	}
	
	public static Double extractNumFromString(final String num) {

		Double result = null;
		Pattern p = Pattern.compile("(([0-9]+)(\\,))?([0-9]+)(\\.)([0-9]{2})");
		Matcher m = p.matcher(num);
		while(m.find()) {
			result = Double.parseDouble(m.group().replaceAll("\\,", ""));
			break;
		}
		return result;
	}
}
