package net.sssssssthedev.SmartClient.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.sssssssthedev.SmartClient.SmartClient;
import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.GL_INFO_LOG_LENGTH;

public class ShaderUtils {

    public ShaderUtils() {
        throw new IllegalStateException("You can't instantiate a utility class.");
    }

    public static int program;
    public static int programID;
    public static int uniformTime;
    public static int uniformRes;

    public static void loadShader(ResourceLocation shaderFile) {
        program = glCreateProgram();
        glAttachShader(program, compileShader(shaderFile, GL_FRAGMENT_SHADER));
        glLinkProgram(program);
        if (glGetProgrami(program, GL_LINK_STATUS) == 0) {
            SmartClient.Logger.error(String.format("Couldn't link shader because of %s", glGetProgrami(program, GL_INFO_LOG_LENGTH)));
        }
        programID = program;
        glUseProgram(program);
        uniformTime = glGetUniformLocation(program, "time");
        uniformRes = glGetUniformLocation(program, "resolution");
        glUseProgram(0);
    }

    public static void renderShader(int width, int height, float time) {
        glUseProgram(programID);

        glUniform2f(uniformRes, width, height);
        glUniform1f(uniformTime, time);
    }

    public static int compileShader(ResourceLocation resourceLocation, int shaderType) {
        int shader = glCreateShader(shaderType);

        try {
            // This is taken from what they are doing in ShaderLoader to get the byteBuffer for the resource
            IResource resource = Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation);
            byte[] bytes = IOUtils.toByteArray(new BufferedInputStream(resource.getInputStream()));
            ByteBuffer byteBuffer = BufferUtils.createByteBuffer(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.position(0);
            glShaderSource(shader, byteBuffer);
            glCompileShader(shader);

            int compileStatus = glGetShaderi(shader, GL_COMPILE_STATUS);

            if (compileStatus == 0) {
                SmartClient.Logger.error(String.format("Couldn't compile shader at %s because of %s", resourceLocation, glGetShaderInfoLog(shader, glGetShaderi(shader, GL_INFO_LOG_LENGTH))));
            }

            return shader;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
