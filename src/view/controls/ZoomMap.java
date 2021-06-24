package view.controls;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Scale;

public class ZoomMap {

    private Node node;

    public void staticZoom(double ratio) {
        Scale scale = new Scale();
        scale.setX(node.getScaleX() * ratio);
        scale.setY(node.getScaleY() * ratio);

        Point2D p = node.parentToLocal(300, 300);
        scale.setPivotX(p.getX());
        scale.setPivotY(p.getY());
        node.getTransforms().add(scale);
    }

    public void dynamicZoom() {
        node.setOnScroll((ScrollEvent event) -> {
            double zoomFactor = 1.05;
            double deltaY = event.getDeltaY();
            if (deltaY < 0) {
                zoomFactor = 2.0 - zoomFactor;
            }

            Scale scale = new Scale();
            scale.setX(node.getScaleX() * zoomFactor);
            scale.setY(node.getScaleY() * zoomFactor);

            Point2D p = node.parentToLocal(300, 300);
            scale.setPivotX(p.getX());
            scale.setPivotY(p.getY());
            node.getTransforms().add(scale);
        });
    }

    public ZoomMap(Node node) {
        this.node = node;
    }
}
