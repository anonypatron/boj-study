import java.io.*;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String line = br.readLine();
		Pattern pattern = Pattern.compile("(100+1+|01)+");
		
		if(pattern.matcher(line).matches()) {
			System.out.print("SUBMARINE");
		}else {
			System.out.print("NOISE");
		}
		
	}
    
}
