class Stack:
    __array = None
    __counter = None

    def __init__(self, limit):
        self.__array = [None] * limit
        self.__counter = 0

    def push(self, item):
        if len(self.__array) == self.__counter:
            raise Exception('The stack is overflow')
        self.__array[self.__counter] = item
        self.__counter += 1

    def pop(self):
        if self.is_empty():
            raise Exception('The stack is empty')
        self.__array[self.__counter - 1] = None
        self.__counter -= 1

    def peek(self):
        return None if self.__counter == 0 \
            else self.__array[self.__counter - 1]

    def is_empty(self):
        return self.__counter == 0

    def __str__(self):
        string = ''
        for index in range(self.__counter):
            string += f'{self.__array[index]} '
        return string
