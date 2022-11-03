class DoubleStack:
    __array = None
    __first_index = None
    __second_index = None

    def __init__(self, limit):
        self.__array = [None] * limit
        self.__first_index = 0
        self.__second_index = limit - 1

    def push_first(self, item):
        if self.is_first_full():
            raise Exception('The stack is overflow')
        self.__array[self.__first_index] = item
        self.__first_index += 1

    def pop_first(self):
        if self.is_first_empty():
            raise Exception('The stack is overflow')
        self.__first_index -= 1
        item = self.__array[self.__first_index]
        self.__array[self.__first_index] = None
        return item

    def is_first_full(self):
        return self.__array[self.__first_index] is not None

    def is_first_empty(self):
        return self.__first_index == 0

    def push_second(self, item):
        if self.is_second_full():
            raise Exception('The stack is overflow')
        self.__array[self.__second_index] = item
        self.__second_index -= 1

    def pop_second(self):
        if self.is_second_empty():
            raise Exception('The stack is empty')
        self.__second_index += 1
        item = self.__array[self.__second_index]
        self.__array[self.__second_index] = None
        return item

    def is_second_full(self):
        return self.__array[self.__second_index] is not None

    def is_second_empty(self):
        return self.__second_index == len(self.__array) - 1

    def __str__(self):
        string = ''
        for item in self.__array:
            if item is not None:
                string += f'{item} '
        return string
