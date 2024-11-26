package com.portafolio.portfolio_backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfolioBackendApplication {

	public static void main(String[] args) {
		String environment = System.getenv("ENVIRONMENT");
		System.out.println(environment);
		if (environment == null || !environment.equals("production")) {
			// Solo carga .env si NO estás en producción
			try {
				// Cargar las variables de entorno desde el archivo .env
				Dotenv dotenv = Dotenv.load();
				System.setProperty("AWS_ACCESS_KEY_ID", dotenv.get("AWS_ACCESS_KEY_ID"));
				System.setProperty("AWS_SECRET_ACCESS_KEY", dotenv.get("AWS_SECRET_ACCESS_KEY"));
				System.setProperty("AWS_ACCESS_REGION", dotenv.get("AWS_ACCESS_REGION"));
				System.out.println("Archivo .env cargado exitosamente.");
			} catch (Exception e) {
				System.out.println("Archivo .env no encontrado. Continuando con variables del sistema.");
			}
		} else {
			System.out.println("Entorno de producción detectado. Usando variables del sistema.");
		}

		// Iniciar la aplicación Spring Boot
		SpringApplication.run(PortfolioBackendApplication.class, args);
	}

}
