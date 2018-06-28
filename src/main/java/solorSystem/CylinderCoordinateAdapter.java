package solorSystem;

import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.WritableValue;
import javafx.geometry.Point3D;

import java.util.Objects;

public class CylinderCoordinateAdapter {

    private final DoubleProperty theta = new SimpleDoubleProperty();
    private final DoubleProperty radius = new SimpleDoubleProperty();
    private final DoubleProperty h = new SimpleDoubleProperty();

    private static final Point3D DEFAULT_AXIS = new Point3D(0, 0, 1);
    private Point3D axis2;
    private Point3D axis3;

    private final ObjectProperty<Point3D> axis = new SimpleObjectProperty<Point3D>() {

        @Override
        public void set(Point3D newValue) {
            newValue = (newValue == null || newValue.equals(Point3D.ZERO)) ? DEFAULT_AXIS : newValue.normalize();

            // find first value ortogonal to axis with z = 0
            axis2 = newValue.getX() == 0 && newValue.getY() == 0 ? new Point3D(1, 0, 0) : new Point3D(-newValue.getY(), newValue.getX(), 0).normalize();

            // find axis ortogonal to the other 2
            axis3 = newValue.crossProduct(axis2);
            super.set(newValue);
        }
    };

    public CylinderCoordinateAdapter(WritableValue<Number> x, WritableValue<Number> y, WritableValue<Number> z) {
        Objects.requireNonNull(x);
        Objects.requireNonNull(y);
        Objects.requireNonNull(z);
        axis.set(DEFAULT_AXIS);
        InvalidationListener listener = o -> {
            Point3D ax = axis.get();
            double h = getH();
            double theta = getTheta();
            double r = getRadius();

            Point3D endPoint = ax.multiply(h).add(axis2.multiply(Math.cos(theta) * r)).add(axis3.multiply(Math.sin(theta) * r));

            x.setValue(endPoint.getX());
            y.setValue(endPoint.getY());
            z.setValue(endPoint.getZ());
        };
        theta.addListener(listener);
        radius.addListener(listener);
        h.addListener(listener);
        axis.addListener(listener);

        listener.invalidated(null);
    }

    public final Point3D getAxis() {
        return this.axis.get();
    }

    public final void setAxis(Point3D value) {
        this.axis.set(value);
    }

    public final ObjectProperty<Point3D> axisProperty() {
        return this.axis;
    }

    public final double getH() {
        return this.h.get();
    }

    public final void setH(double value) {
        this.h.set(value);
    }

    public final DoubleProperty hProperty() {
        return this.h;
    }

    public final double getRadius() {
        return this.radius.get();
    }

    public final void setRadius(double value) {
        this.radius.set(value);
    }

    public final DoubleProperty radiusProperty() {
        return this.radius;
    }

    public final double getTheta() {
        return this.theta.get();
    }

    public final void setTheta(double value) {
        this.theta.set(value);
    }

    public final DoubleProperty thetaProperty() {
        return this.theta;
    }

}
