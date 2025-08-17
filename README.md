# ForoHub Challenge  

Aplicación de foro desarrollada en **Java con Spring Boot**, como parte del desafío **ForoHub**.  
El proyecto implementa un sistema de autenticación con **JWT**, manejo de usuarios y CRUD de tópicos.  

## 📌 Endpoints principales  

### Autenticación
- `POST /login` → Genera token JWT.  

### Tópicos
- `POST /topicos` → Crear un nuevo tópico.  
- `GET /topicos` → Listar todos los tópicos.  
- `GET /topicos/{id}` → Obtener detalle de un tópico.  
- `PUT /topicos/{id}` → Actualizar un tópico.  
- `DELETE /topicos/{id}` → Eliminar un tópico.  

## ▶️ Ejecución  

### Con Maven  
```bash
mvn spring-boot:run
```

### Generar JAR  
```bash
mvn clean package
java -jar target/forohub-0.0.1-SNAPSHOT.jar
```


## 📖 Desafío  

Este proyecto forma parte del **Challenge ForoHub** del programa **Oracle Next Education + Alura Latam**.  
El objetivo es implementar un **foro con CRUD completo, autenticación JWT y persistencia en BD**.  
