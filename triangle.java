import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class triangle extends Application{

	private Circle c1, c2, c3;
	private Line line1, line2, line3;
	private Pane pane;
	double point1x = 100.0, point1y = 300.0;
	double point2x = 300.0, point2y = 300.0;
	double point3x = 300.0, point3y = 100.0;
	double a = 0, b = 0, c = 0;
	double ang1 = 0, ang2 = 0, ang3 = 0;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
	
	@Override
	public void start(Stage stage) throws Exception {
//==================making circles=======================//
	c1 = new Circle();
	c1.setCenterX(point1x);
	c1.setCenterY(point1y);
	c1.setRadius(10.0);
	c1.setFill(Color.RED);
    c1.setOnMousePressed(circleOnMousePressedEventHandler);
    c1.setOnMouseDragged(circleOnMouseDraggedEventHandler);
	
    c2 = new Circle();
	c2.setCenterX(point2x);
	c2.setCenterY(point2y);
	c2.setRadius(10.0);
	c2.setFill(Color.RED);
    c2.setOnMousePressed(circleOnMousePressedEventHandler);
    c2.setOnMouseDragged(circleOnMouseDraggedEventHandler);
	
	c3 = new Circle();
	c3.setCenterX(point3x);
	c3.setCenterY(point3y);
	c3.setRadius(10.0);
	c3.setFill(Color.RED);
    c3.setOnMousePressed(circleOnMousePressedEventHandler);
    c3.setOnMouseDragged(circleOnMouseDraggedEventHandler);
	
 //==============making lines from center of one circle to center of the next circle============//
		line1 = new Line(c1.getCenterX(), c1.getCenterY(), c2.getCenterX(), c2.getCenterY());
		line2 = new Line(c2.getCenterX(), c2.getCenterY(), c3.getCenterX(), c3.getCenterY());		
		line3 = new Line(c3.getCenterX(), c3.getCenterY(), c1.getCenterX(), c1.getCenterY());
		
//===========================calculating length of each line using the center circle points======//
		a = Math.sqrt((c2.getCenterX() - c1.getCenterX()) * (c2.getCenterX() - c1.getCenterX()) + (c2.getCenterY() - c1.getCenterY()) * (c2.getCenterY() - c1.getCenterY()));
		b = Math.sqrt((c3.getCenterX() - c2.getCenterX()) * (c3.getCenterX() - c2.getCenterX()) + (c3.getCenterY() - c2.getCenterY()) * (c3.getCenterY() - c2.getCenterY()));
		c = Math.sqrt((c1.getCenterX() - c3.getCenterX()) * (c1.getCenterX() - c3.getCenterX()) + (c1.getCenterY() - c3.getCenterY()) * (c1.getCenterY() - c3.getCenterY()));
		
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		
		double pi = 3.14159;
		DecimalFormat decform = new DecimalFormat("0.00");		//use to output a double in #.## style

//================calculating angles using the lengths, then converting from rad to deg==========//
		ang1 = Math.acos((b * b - a * a - c * c) / (-2 * a * c)) * (180/pi);
		ang2 = Math.acos((c * c - b * b - a * a) / (-2 * a * b)) * (180/pi);
		ang3 = Math.acos((a * a - b * b - c * c) / (-2 * b * c)) * (180/pi);
		
		
		System.out.println(ang1);
		System.out.println(ang2);
		System.out.println(ang3);
		
		
		System.out.println(decform.format(ang1));
		System.out.println(decform.format(ang2));
		System.out.println(decform.format(ang3));

		
//		Text txt1 = new Text(point1x + 20, point1y - 20, decform.format(ang1));
//		Text txt2 = new Text(point2x - 40, point2y - 20, decform.format(ang2));
//		Text txt3 = new Text(point3x - 20, point3y + 20, decform.format(ang3));

		
//===============================adding degrees of angle as a text========================//
		Text txt1 = new Text(point1x, point1y, decform.format(ang1) + ":1");
		Text txt2 = new Text(point2x, point2y, decform.format(ang2) + ":2");
		Text txt3 = new Text(point3x, point3y, decform.format(ang3) + ":3" );

		pane=new Pane();
		pane.getChildren().addAll(line1, line2, line3, c1, c2, c3, txt1, txt2, txt3);
		Scene sn = new Scene(pane, 800, 600);
	        
		stage.setScene(sn);
		stage.setTitle("Triangle");
		stage.show();
		
	}
	
	 
	    EventHandler<MouseEvent> circleOnMousePressedEventHandler=new EventHandler<MouseEvent>() {
	     
	            @Override
	            public void handle(MouseEvent t) {
	                orgSceneX = t.getSceneX();
	                orgSceneY = t.getSceneY();
	                orgTranslateX = ((Circle)(t.getSource())).getTranslateX();
	                orgTranslateY = ((Circle)(t.getSource())).getTranslateY();
	            }
	        };
	         
	        EventHandler<MouseEvent> circleOnMouseDraggedEventHandler=new EventHandler<MouseEvent>() {
	     
	            @Override
	            public void handle(MouseEvent t) {
	                double offsetX = t.getSceneX() - orgSceneX;
	                double offsetY = t.getSceneY() - orgSceneY;
	                double newTranslateX = orgTranslateX + offsetX;
	                double newTranslateY = orgTranslateY + offsetY;

	                 
	                ((Circle)(t.getSource())).setTranslateX(newTranslateX);
	                ((Circle)(t.getSource())).setTranslateY(newTranslateY);
	            }
	        };


	public static void main(String[] a) {
		launch(a);
	}
	
}
