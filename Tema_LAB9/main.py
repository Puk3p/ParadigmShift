import subprocess

class Handler:
    def __init__(self, successor=None):
        self._successor = successor

    def handle(self, file_content):
        raise NotImplementedError("In subclasa !!!")

class KotlinHandler(Handler):
    def handle(self, file_content):
        if "fun " in file_content and "val " in file_content:
            return "Kotlin"
        elif self._successor:
            return self._successor.handle(file_content)
        return None

class PythonHandler(Handler):
    def handle(self, file_content):
        if "def " in file_content:
            return "Python"
        elif self._successor:
            return self._successor.handle(file_content)
        return None

class BashHandler(Handler):
    def handle(self, file_content):
        if file_content.startswith("#!/bin/bash"):
            return "Bash"
        elif self._successor:
            return self._successor.handle(file_content)
        return None

class JavaHandler(Handler):
    def handle(self, file_content):
        if "public class" in file_content and "public static void main" in file_content:
            return "Java"
        elif self._successor:
            return self._successor.handle(file_content)
        return None

lant = KotlinHandler(PythonHandler(BashHandler(JavaHandler())))

def execute_code(file_type, file_content, filename):
    if file_type == "Python":
        with open(filename, 'w') as file:
            file.write(file_content)
        result = subprocess.run(['python', filename], capture_output=True, text=True)
        return result.stdout
    elif file_type == "Bash":
        with open(filename, 'w') as file:
            file.write(file_content)
        result = subprocess.run(['bash', filename], capture_output=True, text=True)
        return result.stdout
    elif file_type == "Kotlin":
        pass
    elif file_type == "Java":
        pass
    return "Execution not supported for this file type"


file_content = """
def greet():
    fun = val 
    print("Hello, Python!")
greet()
"""
file_type = lant.handle(file_content)
print("Detected file type:", file_type)
execute_output = execute_code(file_type, file_content, "temp.py")
print(execute_output)