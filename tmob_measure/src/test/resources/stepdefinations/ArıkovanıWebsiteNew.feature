Feature: Arıkovanı Website

	Scenario: User Login - Error Credential

		Given Navigate to "https://arikovani.com"
		When Click to button "Giriş Yap"
		And Select tab "Eposta ile Giriş yap"
		Given Enter E-posta Adresi "caglar23@gmail.com"
		And Enter Şifre "Test1234"
		When Click to button "Giriş Yap"
		Then Check message "Geçersiz kullanıcı adı ve/veya şifre"

	Scenario: User Login - Successful

		Given Navigate to "https://arikovani.com"
		When Click to button "Giriş Yap"
		And Select tab "Eposta ile Giriş yap"
		Given Enter text E-posta Adresi "caglar23@gmail.com"
		And Enter text Şifre "492364"
		When Click to button "Giriş Yap"
		Then Check URL "https://arikovani.com/"
		And Check button "Giriş Yap" notvisible

	Scenario: Follow Project

		Given 1.2.2 User Login - Successful
		When Click to link "Fonlama"
		And Click to image "Yeni Hisler Kazandıran Akıllı Saat : Sense Watch"
		And Click to link "Takip Et"
		Then Check message "Proje takip listenize eklendi."
		When Click to button "Tamam"

	Scenario: Register Mail List  – Wrong Mail Format

		Given Navigate to "https ://arikovani.com/"
		And Enter text "<E-posta>"
		When Click to button "Abone Ol"
		Then Check popUpMessage "E-posta adresi geçerli olmalı."
	Examples: 
		| E-posta |
		| test@ |
		|  test |
		|  !3 |


	Scenario: Register Mail List  – Successful

		Given Navigate to "https://arikovani.com"
		And Enter text E-posta "test@test123.com"
		When Click to button "Abone Ol"
		Then Check message "Email adresiniz haber grubuna eklenmiştir."

	Scenario: Update User Profile  – Wrong Format (İsim)

		Given 1.2.2 User Login - Successful
		When Click to link "Profilim"
		Given Enter text "İsim"
		When Click to button "Güncelle"
		Then Check message "Lütfen isminizi yazınız"

	Scenario: Update User Profile  – Wrong Format (Soyisim)

		Given 1.2.2 User Login - Successful
		When Click to link "Profilim"
		Given Enter text "Soyisim"
		When Click to button "Güncelle"
		Then Check message "Lütfen soyisminizi yazınız"

	Scenario: Update User Profile  – Wrong Format (Linkedin)

		Given 1.2.2 User Login - Successful
		When Click to link "Profilim"
		Given Enter text "Linkedin"
		When Click to button "Güncelle"
		Then Check message "Lütfen Linkedin Hesap Linkini Giriniz"

	Scenario: Update User Profile  – Successful

		Given 1.2.2 User Login - Successful
		When Click to link "Profilim"
		Given Enter text İsim "tester-name"
		And Enter text Soyisim "tester-surname"
		And Enter Linkedin "https://tr.linkedin.com/tester-linkedid"
		When Click to button "Güncelle"
		Then Check text İsim "tester-name"
		And Check text Soyisim "tester-surname"
		And Check text Linkedin "https://tr.linkedin.com/tester-linkedid"

