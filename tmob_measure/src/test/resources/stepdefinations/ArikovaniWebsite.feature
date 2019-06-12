Feature: Arikovani


Scenario: User Login - Error Credential

		Given Navigate to web page as "https://arikovani.com/"
		When Select tab of "Giriş Yap"
		When Enter email and paşşword
		 | email    | testwrong@gmail.com |
         | password | Aa123456 |
		And Click to button of "submit"
		#Then Check the message on screen "Geçersiz kullanıcı adı ve/veya şifre"


Scenario: User Login - Successful

	    Given Navigate to web page as "https://arikovani.com/"
		When Select tab of "Giriş Yap"
        When Enter email and password
		    | email    | caglar23@gmail.com |
            | password | 492364 |
		And Click to button of "submit"
		And Exit from webpage

Scenario: Register Mail List  – Successful

        Given Navigate to web page as "https://arikovani.com/"
        And Fill e-mail textbox as "yigithankadioglu123@gmail.com"
        And Click to button of "button-submit-newsletter"
        #Then Check the message on screen "Email adresiniz haber grubuna eklenmiştir."

Scenario: Register Mail List  – Wrong Mail Format

        Given Navigate to web page as "https://arikovani.com/"
        Given Fill e-mail textbox as "yigithankadioglut@gmail.com"
    	And Click to button of "button-submit-newsletter"
    	#Then Check the message on screen "Bu eposta adresi ile daha önce kayıt olunmuştur."
		Then Exit from webpage


Scenario: Update User Profile  – Wrong Format (İsim)

		 Given Navigate to web page as "https://arikovani.com/"
         When Select tab of "Giriş Yap"
         When Enter email and password
            | email    | caglar23@gmail.com |
            | password | 492364 |
        And Click to button of "submit"
		And Click to button of "profile-btn"
		And Click to button of "Profilim"
		When Enter name and surname
		    | name    |         |
            | surname | soyisim |
		And Click to button of "profileUpdateBtn"
		#Then Check the message on screen "Lütfen isminizi yazınız" visible
        Then Exit from webpage


Scenario: Update User Profile  – Wrong Format (Soyisim)

        Given Navigate to web page as "https://arikovani.com/"
        When Select tab of "Giriş Yap"
        When Enter email and password
                    | email    | caglar23@gmail.com |
                    | password | 492364 |
        And Click to button of "submit"
        And Click to button of "profile-btn"
        And Click to button of "Profilim"
        When Enter name and surname
                | name    |     isim    |
                | surname |             |
        And Click to button of "profileUpdateBtn"
        #Then Check the message on screen "Lütfen soyisminizi yazınız" visible
        Then Exit from webpage


Scenario: Update User Profile  – Wrong Format (Linkedin)

		 Given Navigate to web page as "https://arikovani.com/"
         When Select tab of "Giriş Yap"
         When Enter email and password
                    | email    | caglar23@gmail.com |
                    | password | 492364 |
         And Click to button of "submit"
		 And Click to button of "profile-btn"
		 And Click to button of "Profilim"
		 When Enter linkedIn
		        | linkedIn    |        |
		 And Click to button of "profileUpdateBtn"
		 #Then Check parsley-errors-list "Lütfen Linkedin Hesap Linkini Giriniz" visible ""Lütfen Linkedin Hesap Linkini Giriniz"


Scenario: Update User Profile  – Successful

          Given Navigate to web page as "https://arikovani.com/"
          When Select tab of "Giriş Yap"
          When Enter email and password
                 | email    | caglar23@gmail.com |
                 | password | 492364 |
          And Click to button of "submit"
          And Click to button of "profile-btn"
          And Click to button of "Profilim"
          When Enter name and surname
                | name    |     test-name    |
                | surname |     test-surname |
          When Enter linkedIn
         		| linkedIn    |  https://tr.linkedin.com/tester-linkedid   |
          And Click to button of "profileUpdateBtn"