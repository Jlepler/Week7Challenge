package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    MessageRepository repository;


    @Override
    public void run(String... strings) throws Exception{
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ADMIN"));

        Role adminRole = roleRepository.findByRole("ADMIN");
        Role userRole = roleRepository.findByRole("USER");

        User user = new User("Jim@jim.com", "password", "Jim", "Jimmerson", true, "jim");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("admin@admin.com", "password", "Admin", "User", true, "admin");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);

        Message message = new Message("Howdy","Lorem ipsum dolor sit amet, consectetur adipisicing elit. " +
                "Accusamus, accusantium adipisci consequuntur et illum inventore iure maiores.","06/12/2018","Ernest Shackleton");
        repository.save(message);

        message = new Message("Sooo booorrrred","necessitatibus quae aliquid asperiores aut autem explicabo fugit impedit inventore, ipsum nisi omnis. " +
                "Aliquid asperiores aut autem explicabo fugit impedit inventore, ipsum nisi omnis.","01/12/2019","Boaty McBoatface");
        repository.save(message);

        message = new Message("OMG!!!","Lorem ipsum dolor sit amet, consectetur adipisicing elit. " +
                "Accusamus, accusantium adipisci consequuntur et illum inventore","02/12/2019","Inspired Fool");
        repository.save(message);
    }
}
