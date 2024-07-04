package com.example.customer_api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    @GetMapping("/")
    public String welcome() {
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Customer API</title>
            <style>
                body {
                    font-family: 'Arial', sans-serif;
                    margin: 0;
                    padding: 0;
                    background-color: #f0f2f5;
                    color: #333;
                }
                header {
                    background-color: #4CAF50;
                    padding: 20px;
                    text-align: center;
                    color: white;
                }
                main {
                    padding: 20px;
                }
                h1 {
                    color: #4CAF50;
                }
                .container {
                    max-width: 800px;
                    margin: auto;
                    background: white;
                    padding: 20px;
                    border-radius: 8px;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    text-align: center;
                }
                .btn {
                    padding: 10px 20px;
                    margin: 20px 10px;
                    border: none;
                    border-radius: 5px;
                    cursor: pointer;
                    font-size: 16px;
                    color: white;
                }
                .btn-customers {
                    background-color: #4CAF50;
                }
                .btn-customers:hover {
                    background-color: #45a049;
                }
                .btn-swagger {
                    background-color: #007BFF;
                }
                .btn-swagger:hover {
                    background-color: #0056b3;
                }
                footer {
                    background-color: #4CAF50;
                    padding: 10px;
                    text-align: center;
                    color: white;
                    position: fixed;
                    bottom: 0;
                    width: 100%;
                }
                @media (max-width: 600px) {
                    body {
                        font-size: 18px;
                    }
                    header, footer {
                        padding: 15px;
                    }
                    .container {
                        padding: 15px;
                    }
                }
            </style>
        </head>
        <body>
            <header>
                <h1>Customer API</h1>
            </header>
            <main>
                <div class="container">
                    <h2>Bienvenido a la API de Clientes</h2>
                    <p>Este proyecto es una API para manejar clientes usando Spring Boot. Incluye métodos para agregar, actualizar, buscar y eliminar clientes (CRUD).</p>
                    <button class="btn btn-customers" onclick="window.location.href='http://localhost:8080/api/customers'">Ver todos los clientes</button>
                    <button class="btn btn-swagger" onclick="window.location.href='http://localhost:8080/swagger-ui/index.html'">Documentación API</button>
                </div>
            </main>
            <footer>
                &copy; 2024 Customer API. Todos los derechos reservados.
            </footer>
        </body>
        </html>
        """;
    }
}
