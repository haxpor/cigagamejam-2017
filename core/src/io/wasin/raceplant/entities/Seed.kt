package io.wasin.raceplant.entities

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

/**
 * Created by haxpor on 6/17/17.
 */
class Seed(texture: Texture, x: Float, y: Float): Sprite(texture, SPRITE_SIZE, SPRITE_SIZE) {

    companion object {
        const val SPRITE_SIZE: Int = 16
    }

    // animation
    private var idleAnimation: Animation<TextureRegion>
    private var animationTimer: Float = 0f

    init {
        // populate animations
        val tmpFrames = TextureRegion.split(texture, Seed.SPRITE_SIZE, Seed.SPRITE_SIZE)

        // idle
        val idleFrames = Array<TextureRegion>()
        for (col in 0..3) {
            idleFrames.add(tmpFrames[0][col])
        }
        idleAnimation = Animation<TextureRegion>(1 / 7f, idleFrames, Animation.PlayMode.LOOP)

        // place on input position
        this.x = x
        this.y = y
    }

    constructor(texture: Texture): this(texture, 0f, 0f) {}

    fun update(dt: Float) {
        animationTimer += dt

        // set texture region
        var currentFrameRegion = idleAnimation.getKeyFrame(animationTimer)

        if (currentFrameRegion != null) {
            setRegion(currentFrameRegion!!)
        }
    }
}