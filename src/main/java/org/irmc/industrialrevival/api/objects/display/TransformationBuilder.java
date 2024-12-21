package org.irmc.industrialrevival.api.objects.display;

import lombok.Getter;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

@Getter
public class TransformationBuilder {
    private final Vector3f translation = new Vector3f();
    private final Quaternionf leftRotation = new Quaternionf();
    private final Vector3f scale = new Vector3f(1, 1, 1);
    private final Quaternionf rightRotation = new Quaternionf();

    // Translation setters
    public TransformationBuilder setTranslationX(float x) {
        this.translation.x = x;
        return this;
    }

    public TransformationBuilder setTranslationY(float y) {
        this.translation.y = y;
        return this;
    }

    public TransformationBuilder setTranslationZ(float z) {
        this.translation.z = z;
        return this;
    }

    // Left Rotation setters
    public TransformationBuilder setLeftRotationX(float x) {
        this.leftRotation.x = x;
        return this;
    }

    public TransformationBuilder setLeftRotationY(float y) {
        this.leftRotation.y = y;
        return this;
    }

    public TransformationBuilder setLeftRotationZ(float z) {
        this.leftRotation.z = z;
        return this;
    }

    public TransformationBuilder setLeftRotationW(float w) {
        this.leftRotation.w = w;
        return this;
    }

    // Scale setters
    public TransformationBuilder setScaleX(float x) {
        this.scale.x = x;
        return this;
    }

    public TransformationBuilder setScaleY(float y) {
        this.scale.y = y;
        return this;
    }

    public TransformationBuilder setScaleZ(float z) {
        this.scale.z = z;
        return this;
    }

    // Right Rotation setters
    public TransformationBuilder setRightRotationX(float x) {
        this.rightRotation.x = x;
        return this;
    }

    public TransformationBuilder setRightRotationY(float y) {
        this.rightRotation.y = y;
        return this;
    }

    public TransformationBuilder setRightRotationZ(float z) {
        this.rightRotation.z = z;
        return this;
    }

    public TransformationBuilder setRightRotationW(float w) {
        this.rightRotation.w = w;
        return this;
    }

    public Transformation build() {
        return new Transformation(translation, leftRotation, scale, rightRotation);
    }
}
