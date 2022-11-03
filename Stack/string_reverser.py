class StringReverser:
    @staticmethod
    def reverse(string):
        reverse_string = ''
        stack = []
        for character in string:
            stack.append(character)
        while stack:
            reverse_string += stack.pop()
        return reverse_string

