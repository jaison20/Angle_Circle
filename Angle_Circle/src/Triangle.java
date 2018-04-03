import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class triangle extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {

		Pane pane=new Pane();
		   Group designer = movingTriangle();
		    designer.setLayoutX(200);
		    designer.setLayoutY(200);		
		
		pane.getChildren().addAll(designer);
		Scene sn = new Scene(pane, 800, 600);
	        
		stage.setScene(sn);
		stage.setTitle("Triangle");
		stage.show();
		
	}
	
	double xMouse,yMouse;
	private Circle c1, c2, c3, cir1;
	private Line line1, line2, line3;
	double point1x = 100.0, point1y = 300.0;
	double point2x = 300.0, point2y = 300.0;
	double point3x = 300.0, point3y = 100.0;
	private Text txt1, txt2, txt3;
	double a = 0, b = 0, c = 0;
	double ang1 = 0, ang2 = 0, ang3 = 0;
	
	public Group movingTriangle(){
	        	
		
		cir1 = new Circle();
		cir1.setCenterX(200);
		cir1.setCenterY(200);
		cir1.setRadius(150);
		cir1.setFill(null);
		cir1.setStroke(Color.BLACK);
		
        //==================making circles=======================//
	     	c1 = new Circle();
	       	c1.setCenterX(point1x);
        	c1.setCenterY(point1y);
        	c1.setRadius(10.0);
	       	c1.setFill(Color.RED);
	        c1.setStroke(Color.BLACK);
	        	
	        c2 = new Circle();
	        c2.setCenterX(point2x);
	       	c2.setCenterY(point2y);
	       	c2.setRadius(10.0);
	       	c2.setFill(Color.RED);
            c2.setStroke(Color.BLACK);
	        	
	        c3 = new Circle();
	       	c3.setCenterX(point3x);
	       	c3.setCenterY(point3y);
	       	c3.setRadius(10.0);
	       	c3.setFill(Color.RED);
	        c3.setStroke(Color.BLACK);
	            
	    //==============making lines from center of one circle to center of the next circle============//
	    //line1 = new Line(c1.getCenterX(), c1.getCenterY(), c2.getCenterX(), c2.getCenterY());
	   		line1 = new Line();
	   		line1.startXProperty().bind(c1.centerXProperty());
	   		line1.startYProperty().bind(c1.centerYProperty());		
	   		line1.endXProperty().bind(c2.centerXProperty());
    		line1.endYProperty().bind(c2.centerYProperty());
	    		
	    	line2 = new Line();
	    	line2.startXProperty().bind(c2.centerXProperty());
	   		line2.startYProperty().bind(c2.centerYProperty());		
	   		line2.endXProperty().bind(c3.centerXProperty());
	   		line2.endYProperty().bind(c3.centerYProperty());
	    		
	    	line3 = new Line();
	    	line3.startXProperty().bind(c3.centerXProperty());
	   		line3.startYProperty().bind(c3.centerYProperty());		
	   		line3.endXProperty().bind(c1.centerXProperty());
	   		line3.endYProperty().bind(c1.centerYProperty());
	    		
	 //===========================calculating length of each line using the center circle points======//
	  		a = Math.sqrt((c2.getCenterX() - c1.getCenterX()) * (c2.getCenterX() - c1.getCenterX()) + (c2.getCenterY() - c1.getCenterY()) * (c2.getCenterY() - c1.getCenterY()));
	   		b = Math.sqrt((c3.getCenterX() - c2.getCenterX()) * (c3.getCenterX() - c2.getCenterX()) + (c3.getCenterY() - c2.getCenterY()) * (c3.getCenterY() - c2.getCenterY()));
	  		c = Math.sqrt((c1.getCenterX() - c3.getCenterX()) * (c1.getCenterX() - c3.getCenterX()) + (c1.getCenterY() - c3.getCenterY()) * (c1.getCenterY() - c3.getCenterY()));

	    	double pi = 3.14159;
	  		DecimalFormat decform = new DecimalFormat("0.00");		//use to output a double in #.## style

	//================calculating angles using the lengths, then converting from rad to deg==========//
	    	ang1 = Math.acos((b * b - a * a - c * c) / (-2 * a * c)) * (180/pi);
	  		ang2 = Math.acos((c * c - b * b - a * a) / (-2 * a * b)) * (180/pi);
	    	ang3 = Math.acos((a * a - b * b - c * c) / (-2 * b * c)) * (180/pi);

	//===============================adding degrees of angle as a text========================//
	    	txt1 = new Text(point1x, point1y, decform.format(ang1) + ":1");
	    	txt2 = new Text(point2x, point2y, decform.format(ang2) + ":2");
	    	txt3 = new Text(point3x, point3y, decform.format(ang3) + ":3" );
	    				
	        c1.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xMouse = event.getX();
	                yMouse = event.getY();
	                c1.setCenterX(xMouse);
	                c1.setCenterY(yMouse);
		   			a = Math.sqrt((c2.getCenterX() - c1.getCenterX()) * (c2.getCenterX() - c1.getCenterX()) + (c2.getCenterY() - c1.getCenterY()) * (c2.getCenterY() - c1.getCenterY()));
		    		b = Math.sqrt((c3.getCenterX() - c2.getCenterX()) * (c3.getCenterX() - c2.getCenterX()) + (c3.getCenterY() - c2.getCenterY()) * (c3.getCenterY() - c2.getCenterY()));
		   			c = Math.sqrt((c1.getCenterX() - c3.getCenterX()) * (c1.getCenterX() - c3.getCenterX()) + (c1.getCenterY() - c3.getCenterY()) * (c1.getCenterY() - c3.getCenterY()));
	    			ang1 = Math.acos((b * b - a * a - c * c) / (-2 * a * c)) * (180/pi);
	   				ang2 = Math.acos((c * c - b * b - a * a) / (-2 * a * b)) * (180/pi);
	   				ang3 = Math.acos((a * a - b * b - c * c) / (-2 * b * c)) * (180/pi);
                    txt1.setText(decform.format(ang1) + ":1");
                    txt1.setX(xMouse);
	                txt1.setY(yMouse);
	                txt2.setText(decform.format(ang2) + ":2");
	                txt3.setText(decform.format(ang3) + ":3");
	                }
	            });

	        c2.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xMouse = event.getX();
	                yMouse = event.getY();
	                c2.setCenterX(xMouse);
	                c2.setCenterY(yMouse);
	                a = Math.sqrt((c2.getCenterX() - c1.getCenterX()) * (c2.getCenterX() - c1.getCenterX()) + (c2.getCenterY() - c1.getCenterY()) * (c2.getCenterY() - c1.getCenterY()));
	                b = Math.sqrt((c3.getCenterX() - c2.getCenterX()) * (c3.getCenterX() - c2.getCenterX()) + (c3.getCenterY() - c2.getCenterY()) * (c3.getCenterY() - c2.getCenterY()));
		    		c = Math.sqrt((c1.getCenterX() - c3.getCenterX()) * (c1.getCenterX() - c3.getCenterX()) + (c1.getCenterY() - c3.getCenterY()) * (c1.getCenterY() - c3.getCenterY()));
	    			ang1 = Math.acos((b * b - a * a - c * c) / (-2 * a * c)) * (180/pi);
	    			ang2 = Math.acos((c * c - b * b - a * a) / (-2 * a * b)) * (180/pi);
	    			ang3 = Math.acos((a * a - b * b - c * c) / (-2 * b * c)) * (180/pi);
	   	            txt1.setText(decform.format(ang1) + ":1");
	   	            txt2.setText(decform.format(ang2) + ":2");
	                txt2.setX(xMouse);
	                txt2.setY(yMouse);
	                txt3.setText(decform.format(ang3) + ":3");
	                }
	            });
	            
	        c3.setOnMouseDragged(new EventHandler<MouseEvent>() {
	             @Override
	             public void handle(MouseEvent event) {
	            	xMouse = event.getX();
	                yMouse = event.getY();
	                c3.setCenterX(xMouse);
	                c3.setCenterY(yMouse);
	                a = Math.sqrt((c2.getCenterX() - c1.getCenterX()) * (c2.getCenterX() - c1.getCenterX()) + (c2.getCenterY() - c1.getCenterY()) * (c2.getCenterY() - c1.getCenterY()));
		    		b = Math.sqrt((c3.getCenterX() - c2.getCenterX()) * (c3.getCenterX() - c2.getCenterX()) + (c3.getCenterY() - c2.getCenterY()) * (c3.getCenterY() - c2.getCenterY()));
		    		c = Math.sqrt((c1.getCenterX() - c3.getCenterX()) * (c1.getCenterX() - c3.getCenterX()) + (c1.getCenterY() - c3.getCenterY()) * (c1.getCenterY() - c3.getCenterY()));
	    			ang1 = Math.acos((b * b - a * a - c * c) / (-2 * a * c)) * (180/pi);
	   				ang2 = Math.acos((c * c - b * b - a * a) / (-2 * a * b)) * (180/pi);
	   				ang3 = Math.acos((a * a - b * b - c * c) / (-2 * b * c)) * (180/pi);
	   	            txt1.setText(decform.format(ang1) + ":1");
	   	            txt2.setText(decform.format(ang2) + ":2");
	                txt3.setText(decform.format(ang3) + ":3");
	                txt3.setX(xMouse);
	                txt3.setY(yMouse);
	                }
	            });
	            
			return new Group(line1, line2, line3, c1, c2, c3, txt1, txt2, txt3, cir1);
	        }

	public static void main(String[] a) {
		launch(a);
	}
	
}

