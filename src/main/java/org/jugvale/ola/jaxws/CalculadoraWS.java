package org.jugvale.ola.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class CalculadoraWS {

    @WebMethod
	public double fazerOp(@WebParam(name = "num1") double a
						, @WebParam(name = "num2") double b
						, @WebParam(name = "op") String op) {
		switch (op) {
		case "+":
			return a + b;
		case "-":
			return a - b;
		case "*":
			return a * b;
		case "/":
			return a / b;
		default:
			throw new IllegalArgumentException("Operação '" + op
					+ "' não reconhecida. Informa '+', '-', '*' ou '/'.");
		}
	}

	@WebMethod
	public String sayHello(String name){
    	return "Say Hello to " + name;
	}
        
    @WebMethod
    public String hexConverter(@WebParam(name = "hexcolor") String hexcolor) {
        
        //To RGB
        int R = Integer.valueOf( hexcolor.substring( 1, 3 ), 16 );
        int G = Integer.valueOf( hexcolor.substring( 3, 5 ), 16 );
        int B = Integer.valueOf( hexcolor.substring( 5, 7 ), 16 );
        double computedC, computedM, computedY, computedK;
        String RGBColor = "RGB: " + R + ", " + G + ", " + B;
        String CMYKColor, HSVColor, HSLColor, color;
        
        //To CMYK
        double percentageR = R / 255.0 * 100;
        double percentageG = G / 255.0 * 100;
        double percentageB = B / 255.0 * 100;
        
        double K = 100 - Math.max(Math.max(percentageR, percentageG), percentageB);

        if (K == 100) {
            CMYKColor = "CYMK: 0%, 0%, 0%, 100%" ;
        }

        int c = (int) Math.round(((100 - percentageR - K) / (100 - K) * 100));
        int m = (int) Math.round(((100 - percentageG - K) / (100 - K) * 100));
        int y = (int) Math.round(((100 - percentageB - K) / (100 - K) * 100));
        int k = (int) Math.round(K);
        
        CMYKColor = "CMYK: " + c + "%, " + m + "%, " + y + "%, " + k + "%";
        
        //To HSV
        double r = R / 255.0;
        double g = G / 255.0;
        double b = B / 255.0;
        
        // h, s, v = hue, saturation, value
        double cmax = Math.max(r, Math.max(g, b)); // maximum of r, g, b
        double cmin = Math.min(r, Math.min(g, b)); // minimum of r, g, b
        double diff = cmax - cmin; // diff of cmax and cmin.
        double h = -1, s = -1, l;
        
        // if cmax and cmax are equal then h = 0
        if (cmax == cmin)
            h = 0;
 
        // if cmax equal r then compute h
        else if (cmax == r)
            h = (60 * ((g - b) / diff) + 360) % 360;
 
        // if cmax equal g then compute h
        else if (cmax == g)
            h = (60 * ((b - r) / diff) + 120) % 360;
 
        // if cmax equal b then compute h
        else if (cmax == b)
            h = (60 * ((r - g) / diff) + 240) % 360;
 
        // if cmax equal zero
        if (cmax == 0)
            s = 0;
        else
            s = (diff / cmax) * 100;
 
        // compute v
        double v = cmax * 100;
        int H = (int) Math.round(h);
        int S = (int) Math.round(s);
        int V = (int) Math.round(v);
        HSVColor = "HSV: " + H + "°, " + S + "%, " + V + "%";
        
        //To HSL
        l = (cmax + cmin)/2;
        
        if (diff == 0) {
            s = 0;
        } else {
            s = diff / (1 - Math.abs(2 * l - 1));
        }
        
        s*=100;
        l*=100;
        
        int H_ = (int) Math.round(h);
        int S_ = (int) Math.round(s);
        int L = (int) Math.round(l);
        HSLColor = "HSL: " + H_ + "°, " + S_ + "%, " + L + "%";
        
        color = RGBColor + "\n " + CMYKColor + "\n " + HSVColor + "\n " + HSLColor;
        
        return color;
    }
}
