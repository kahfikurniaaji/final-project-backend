package com.serversidecar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.serversidecar.model.Profile;
import com.serversidecar.model.Role;
import com.serversidecar.model.User;
import com.serversidecar.model.dto.request.EmailRequest;
import com.serversidecar.model.dto.request.ProfileRequest;
import com.serversidecar.repository.ProfileRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProfileService {

    private ProfileRepository profileRepository;

    private RoleService roleService;

    private JavaMailSenderService javaMailSenderService;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    public Profile getById(Long id) {
        return profileRepository.getById(id);
    }

    public Profile create(ProfileRequest profileRequest) {
        Profile profile = new Profile();
        profile.setNik(profileRequest.getNik());
        profile.setName(profileRequest.getName());
        profile.setEmail(profileRequest.getEmail());
        profile.setAdress(profileRequest.getAddress());
        profile.setPhone(profileRequest.getPhone());

        User user = new User();
        user.setUsername(profileRequest.getUsername());
        user.setPassword(passwordEncoder.encode(profileRequest.getPassword())); // Password Encoder ke Encrypt
        user.setIsEnabled(Boolean.TRUE); // Akun itu dikunci atau tidak?
        user.setIsAccountLocked(Boolean.FALSE);// Digunakan untuk verifikasi Akun
        List<Role> role = new ArrayList();
        role.add(roleService.getById(1L));
        user.setRoles(role);
        profile.setUser(user);
        user.setProfile(profile);

//      send mail after create profile
       Profile e = profileRepository.save(profile);

       javaMailSenderService.sendHtmlEmailRequest(new EmailRequest(profileRequest.getEmail(),
               "Welcome Home " + profileRequest.getUsername(), 
               "Selamat Anda Terlah berhasil terdaftar pada program kami."));

       return e;
    }

    public Profile update(Long id, Profile profile) {
        Profile oldProfile = getById(id);
        profile.setId(id);
        profile.setUser(oldProfile.getUser());
        return profileRepository.save(profile);
    }

    public Profile delete(Long id) {
        Profile profile = getById(id);
        profileRepository.delete(profile);
        return profile;
    }

    // JPQL
    public void findByName(String name) {
        if (profileRepository.findByName(name).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Profile already exists");
        }
    }
}