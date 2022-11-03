class Expression:
    __open_brackets = ['(', '<', '[', '{']
    __closed_brackets = [')', '>', ']', '}']

    def __is_left_bracket(self, value):
        return value in self.__open_brackets

    def __is_right_bracket(self, value):
        return value in self.__closed_brackets

    def __is_brackets_match(self, left_value, right_value):
        return self.__open_brackets.index(left_value) == self.__closed_brackets.index(right_value)

    def is_balance(self, string):
        stack = []
        for item in string:
            if self.__is_left_bracket(item):
                stack.append(item)
            elif self.__is_right_bracket(item):
                if not stack:
                    return False
                else:
                    open_bracket = stack.pop()
                    if not self.__is_brackets_match(open_bracket, item):
                        return False
        return True if not stack else False
