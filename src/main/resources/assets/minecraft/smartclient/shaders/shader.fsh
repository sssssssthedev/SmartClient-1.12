#version 120

varying vec4 FragColor;

uniform float time;
uniform vec2 mouse;
uniform vec2 resolution;


void main()
{
	vec2 position = ( gl_FragCoord.xy / resolution.xy );

	vec3 color1 = vec3(1.0, 0.3, 1.0);
	vec3 color2 = vec3(0, 1, 1);
	float gradientDir = 90.0;
	vec3 gradientColor = mix(color1, color2, position.x + sin(time));
	gl_FragColor = vec4(gradientColor.rgb, 1.0);
}