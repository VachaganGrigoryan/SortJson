import json


def _indent(s):
    return ''.join(s.splitlines())


def _format_value(value):
    return _indent(_generate_pprint_json(value))


def _generate_pprint_json(value):
    return json.dumps(value, sort_keys=True, indent=0)


def _bottom_up_sort(unsorted_json):
    if isinstance(unsorted_json, list):
        return sorted([_bottom_up_sort(item) for item in unsorted_json])

    elif isinstance(unsorted_json, dict):
        return {key:_bottom_up_sort(unsorted_json[key]) for key in sorted(unsorted_json)}

    return unsorted_json


def main():
    str_json = open("json_file.json").read()
    return _format_value(_bottom_up_sort(json.loads(str_json)))

print main()
