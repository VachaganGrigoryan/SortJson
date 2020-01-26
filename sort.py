import json

def sort_list(new_list):
    return [(key, val if not isinstance(val, dict) else sort_list(val)) for key, val in new_list.items()]


def _bottom_up_sort(unsorted_json):

    if isinstance(unsorted_json, list):
        new_list = [_bottom_up_sort(element) for element in unsorted_json]
        if any(isinstance(el, dict) for el in new_list):
            return sorted(new_list, key=sort_list)
        return sorted(new_list)

    elif isinstance(unsorted_json, dict):
        new_dict = {key:_bottom_up_sort(unsorted_json[key]) for key in sorted(unsorted_json)}
        return new_dict

    else:
        return unsorted_json




def main():
    str_json = open("json_file.json").read()
    return _bottom_up_sort(json.loads(str_json))

print(main())