package app.controllers;

import app.config.HibernateConfig;
import app.persistence.dao.impl.AdminDAO;
import app.persistence.dao.impl.SecretKeyDAO;
import app.persistence.dto.AdminDTO;
import app.utils.JwtUtil;
import io.javalin.http.Context;
import io.javalin.http.UnauthorizedResponse;
import jakarta.persistence.EntityManagerFactory;
import org.mindrot.jbcrypt.BCrypt;
import java.util.Optional;

public class AdminController {
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private final AdminDAO adminDAO = AdminDAO.getInstance(emf);
    private final SecretKeyDAO secretKeyDAO = SecretKeyDAO.getInstance(emf);

    public void signUp(Context ctx) {
        AdminDTO admin = ctx.bodyAsClass(AdminDTO.class);
        String secretKeyFromRequest = ctx.header("Authorization");

        if (secretKeyFromRequest == null || !admin.validateSecretKey(secretKeyDAO, secretKeyFromRequest)) {
            throw new UnauthorizedResponse("Invalid or expired secret key");
        }

        adminDAO.save(admin);
        ctx.status(201).json("Admin registered successfully");
    }

    public void login(Context ctx) {
        AdminDTO credentials = ctx.bodyAsClass(AdminDTO.class);
        Optional<AdminDTO> admin = adminDAO.findByEmail(credentials.getEmail());

        if (admin.isPresent()) {
            // Retrieve the hashed password from the database
            String storedHashedPassword = admin.get().getPassword();

            // Compare the entered password with the stored hashed password
            if (BCrypt.checkpw(credentials.getPassword(), storedHashedPassword)) {
                String token = JwtUtil.generateToken(admin.get().getEmail());
                ctx.json(token);
            } else {
                throw new UnauthorizedResponse("Invalid credentials");
            }
        } else {
            throw new UnauthorizedResponse("Invalid credentials");
        }
    }

}
