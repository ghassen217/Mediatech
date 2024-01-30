package com.fstg.mediatech.dto;

import com.fstg.mediatech.annotation.IpAdress;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDto {
    //@NotNull(message = "le nom est obligatoire")
    @NotBlank
    @Size (min = 4, max = 10, message = "Le nombre de caractère doit etre compris entre 4 et 10")
    private String nom;
    //@NotNull(message = "le prenom est obligatoire")
    @NotBlank
    @Size (min = 4, max = 10, message = "Le nombre de caractère doit etre compris entre 4 et 10")
    private String prenom;
    @Nullable
    @Pattern(regexp = "^(?:(?:\\+|00)33|0)\\s*[1-9](?:[\\s.-]*\\d{2}){4}$", message = "Format téléphone invalide")
    private String telephone;
   // @IpAdress()
   // private  String ip;
}
