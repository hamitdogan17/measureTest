Feature: Mail Process

	Scenario: Arı Kovanı Mail Listesi Kaydolma

		Given Navigate to web page as "https://arikovani.com/"
		And Fill e-mail  textbox as "bensutest@gmail.com"
		And Click to button of "button-submit-newsletter"
		Then Check the message on screen "Email adresiniz haber grubuna eklenmiştir."
		Given Fill e-mail  textbox as "bensutest@gmail.com"
		And Click to button of "button-submit-newsletter"
		Then Check the message on screen "Bu eposta adresi ile daha önce kayıt olunmuştur."
		And Exit from webpage

