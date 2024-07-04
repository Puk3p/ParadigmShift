import pygame
import math
import random
import sqlite3

pygame.init()
LATIME, INALTIME = 900, 600
afisaj = pygame.display.set_mode((LATIME, INALTIME))
pygame.display.set_caption("Spanzuratoarea!")

# Variabile pentru butoane
RAZA = 20
GAP = 15
butoane = []
startx = round((LATIME - (RAZA * 2 + GAP) * 13) / 2)
starty = 400
A = 65
for i in range(26):
    x = startx + GAP * 2 + ((RAZA * 2 + GAP) * (i % 13))
    y = starty + ((i // 13) * (GAP + RAZA * 2))
    butoane.append([x, y, chr(A + i), True])

# Fonturi
FONT_LITERA = pygame.font.SysFont('comicsans', 40)
FONT_CUVANT = pygame.font.SysFont('comicsans', 60)
FONT_TITLU = pygame.font.SysFont('comicsans', 70)

# Incarcare imagini
imagini = []
for i in range(7):
    imagine = pygame.image.load("sp" + str(i) + ".png")
    imagini.append(imagine)

stare_spanzuratoare = 0
cuvinte = ["MAMA", "TATA"]
cuvant = random.choice(cuvinte)
ghicit = []

ALB = (255, 255, 255)
NEGRU = (0, 0, 0)


def deseneaza(nume_utilizator):
    afisaj.fill(ALB)

    text_nume = FONT_CUVANT.render("Utilizator: " + nume_utilizator, 1, NEGRU)
    afisaj.blit(text_nume, (10, INALTIME - text_nume.get_height() - 10))
    # Deseneaza titlul
    text = FONT_TITLU.render("SPANZURATOAREA GUMBALL", 1, NEGRU)
    afisaj.blit(text, (LATIME / 2 - text.get_width() / 2, 20))

    # Deseneaza cuvantul
    cuvant_afisat = ""
    for litera in cuvant:
        if litera in ghicit:
            cuvant_afisat += litera + " "
        else:
            cuvant_afisat += "_ "
    text = FONT_CUVANT.render(cuvant_afisat, 1, NEGRU)
    afisaj.blit(text, (400, 200))

    # Deseneaza butoanele
    for litera in butoane:
        x, y, ltr, vizibil = litera
        if vizibil:
            pygame.draw.circle(afisaj, NEGRU, (x, y), RAZA, 3)
            text = FONT_LITERA.render(ltr, 1, NEGRU)
            afisaj.blit(text, (x - text.get_width() / 2, y - text.get_height() / 2))

    afisaj.blit(imagini[stare_spanzuratoare], (150, 100))
    pygame.display.update()


def mesaj(mesaj):
    pygame.time.delay(1000)
    afisaj.fill(ALB)
    text = FONT_CUVANT.render(mesaj, 1, NEGRU)
    afisaj.blit(text, (LATIME / 2 - text.get_width() / 2, INALTIME / 2 - text.get_height() / 2))
    pygame.display.update()
    pygame.time.delay(3000)


def ecran_pornire():
    afisaj.fill(ALB)
    introducere_text = FONT_CUVANT.render("Introdu numele de utilizator:", 1, NEGRU)
    afisaj.blit(introducere_text, (LATIME / 2 - introducere_text.get_width() / 2, 200))
    pygame.display.update()

    nume_utilizator = ""
    introducere = True
    while introducere:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_RETURN:
                    introducere = False
                elif event.key == pygame.K_BACKSPACE:
                    nume_utilizator = nume_utilizator[:-1]
                else:
                    nume_utilizator += event.unicode

        afisaj.fill(ALB)
        introducere_text = FONT_CUVANT.render("Introdu numele de utilizator: " + nume_utilizator, 1, NEGRU)
        afisaj.blit(introducere_text, (LATIME / 2 - introducere_text.get_width() / 2, 200))
        pygame.display.update()

    return nume_utilizator

def conectare_baza_date():
    conn = sqlite3.connect('joc_spanzuratoarea.db')
    cursor = conn.cursor()
    cursor.execute('CREATE TABLE IF NOT EXISTS rezultate (id INTEGER PRIMARY KEY, nume TEXT, rezultat TEXT)')
    conn.commit()
    return conn, cursor

def salvare_in_baza_de_date(nume_utilizator, rezultat):
    conn, cursor = conectare_baza_date()
    cursor.execute('INSERT INTO rezultate (nume, rezultat) VALUES (?, ?)', (nume_utilizator, rezultat))
    conn.commit()
    conn.close()

def mini_meniu():
    afisaj.fill(ALB)
    text_da = FONT_CUVANT.render("Vrei sa reiei? (Da/Nu)", 1, NEGRU)
    text_nu = FONT_CUVANT.render("Apasa 'D' pentru Da sau 'N' pentru Nu", 1, NEGRU)
    afisaj.blit(text_da, (LATIME / 2 - text_da.get_width() / 2, 200))
    afisaj.blit(text_nu, (LATIME / 2 - text_nu.get_width() / 2, 250))
    pygame.display.update()

    while True:
        for event in pygame.event.get():
            if event.type == pygame.KEYDOWN:
                if event.key == pygame.K_d:
                    return True
                elif event.key == pygame.K_n:
                    return False

def main():
    global stare_spanzuratoare, cuvant

    nume_utilizator = ecran_pornire()

    stare_spanzuratoare = 0
    cuvant = random.choice(cuvinte)
    ghicit = []

    FPS = 60
    ceas = pygame.time.Clock()
    ruleaza = True

    while ruleaza:
        ceas.tick(FPS)

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                ruleaza = False
            if event.type == pygame.MOUSEBUTTONDOWN:
                m_x, m_y = pygame.mouse.get_pos()
                for litera in butoane:
                    x, y, ltr, vizibil = litera
                    if vizibil:
                        distanta = math.sqrt((x - m_x) ** 2 + (y - m_y) ** 2)
                        if distanta < RAZA:
                            litera[3] = False
                            ghicit.append(ltr)
                            if ltr not in cuvant:
                                stare_spanzuratoare += 1

        deseneaza(nume_utilizator)

        castigat = True
        for litera in cuvant:
            if litera not in ghicit:
                castigat = False
                break

        if castigat:
            mesaj("Ai CASTIGAT, " + nume_utilizator + "!")
            if not mini_meniu():
                ruleaza = False
            else:
                stare_spanzuratoare = 0
                ghicit.clear()
                cuvant = random.choice(cuvinte)
            salvare_in_baza_de_date(nume_utilizator, 'castigat')
            break

        if stare_spanzuratoare == 6:
            mesaj("Ai PIERDUT, " + nume_utilizator + "!")
            if not mini_meniu():
                ruleaza = False
            else:
                stare_spanzuratoare = 0
                ghicit.clear()
                cuvant = random.choice(cuvinte)
            salvare_in_baza_de_date(nume_utilizator, 'pierdut')
            break


if __name__ == "__main__":
    conn, cursor = conectare_baza_date()
    ruleaza = True
    while ruleaza:
        main()
        if not mini_meniu():
            ruleaza = False

    pygame.quit()