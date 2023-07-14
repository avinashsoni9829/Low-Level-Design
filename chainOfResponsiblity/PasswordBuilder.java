package org.example.chainOfResponsiblity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PasswordBuilder {
  private List<String> errors;
  public String password;

  public PasswordBuilder(List<String> errors , String password)
  {
       this.errors = errors;
       this.password = password;
  }


  public PasswordBuilder lengthCheck(){
      if(password.length() < 6)
      {
          errors.add("password length should be greater than 6");
      }
      return this;
  }

  public PasswordBuilder upperLetterCheck()
  {
      if(!password.matches(".*[A-Z].*"))
          errors.add("password should have at least one capital Alphabet");
      return this;
  }

  public PasswordBuilder specialCharacterCheck()
  {
          if(!password.matches(".*[@!#*].*"))
          errors.add("password should contain at least one special character @ ! # * ");
      return this;
  }

  public PasswordBuilder numericCharacterCheck()
  {
      if(!password.matches(".*\\d.*"))
          errors.add("password should contain at least 1 numeric character");
      return this;
  }

  public void validatePassword(){
      if(errors.isEmpty()) System.out.println("Password is correct");
      else
          for(String error : errors)
          {
              System.out.println(error);
          }
  }


}
