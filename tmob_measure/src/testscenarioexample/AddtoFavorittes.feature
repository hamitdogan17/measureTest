Feature: Add to Favorittes

	Scenario: Adding to the Favorittes

		Given User should be logged in
		And Click tab menu item of "Fonlama"
		And Click to the product of "Yeni Hisler Kazandıran Akıllı Saat"
		And Click to button of "Takip Et"
		Then Check pop up message of "Proje takip listenize eklendi."
		And Exit from webpage

