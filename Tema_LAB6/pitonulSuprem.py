import os

class GenericFile():
    def get_path(self):
        raise NotImplementedError("Nu este implementata")
    def get_freq(self):
        raise NotImplementedError("Nu este implementata")

class TextASCII(GenericFile):
    def __init__(self, path, frecvente):
        self.path = path
        self.frecvente = frecvente

    def get_path(self):
        return self.path

    def get_freq(self):
        return self.frecvente

class TextUNICODE(GenericFile):
    def __init__(self, path, frecvente):
        self.path = path
        self.frecvente = frecvente

    def get_path(self):
        return self.path

    def get_freq(self):
        return self.frecvente

class Binary(GenericFile):
    def __init__(self, path, frecvente):
        self.path = path
        self.frecvente = frecvente

    def get_path(self):
        return self.path

    def get_freq(self):
        return self.frecvente

class XMLFile(TextASCII):
    def __init__(self, path, frecvente, first_tag):
        super().__init__(path, frecvente)
        self.first_tag = first_tag

    def get_first_tag(self):
        return self.first_tag

class BMP(Binary):
    def __init__(self, path, frecvente, width, height, bpp):
        super.__init__(path, frecvente)
        self.width = width
        self.height = height
        self.bpp = bpp

    def show_info(self):
        print("Width:", self.width)
        print("Height:", self.height)
        print("Bits per pixel:", self.bpp)

ROOT_DIR = os.path.abspath("/home/george/Documente/PP/Teme/Tema_LAB6/pitonulMare")

def get_file_type(file_path):
    with open(file_path, 'rb') as f:
        content = f.read()
        if content[:2] == b'BM':
            return 'bmp'
        elif "<?xml" in content.decode('utf-8', errors='ignore'):
            return 'xml_ascii'
        elif "<?xml" in content.decode('utf-16-le', errors='ignore'):
            return 'xml_unicode'
    return None

def get_bmp_info(file_path):
    with open(file_path, 'rb') as f:
        f.seek(18)
        width = int.from_bytes(f.read(4), byteorder='little')
        f.seek(22)
        height = int.from_bytes(f.read(4), byteorder='little')
        f.seek(28)
        bpp = int.from_bytes(f.read(2), byteorder='little')
    return width, height, bpp

def parcugereDirector(directory):
    for root, subdirs, files in os.walk(ROOT_DIR):
        for file in os.listdir(root):
            file_path = os.path.join(root, file)
            if os.path.isfile(file_path):
                # deschide fișierul spre acces binar
                #print(file_path)
                file_type = get_file_type(file_path)
                if file_type == 'xml_ascii':
                    print("XML ASCII:", file_path)
                elif file_type == 'xml_unicode':
                    print("XML UNICODE:", file_path)
                elif file_type == 'bmp':
                    width, height, bpp = get_bmp_info(file_path)
                    print("BMP:", file_path, "- Width:", width, "- Height:", height, "- Bits per pixel:", bpp)

parcugereDirector(ROOT_DIR)