delete from dict_place where placeLevel>0 and firstID not in (3422, 5543, 5542, 5541, 5539, 5538, 5537, 4444, 4156, 18888);
delete from dict_place_config where placeID not in(select placeID from dict_place);
delete from dict_place_game where placeID not in(select placeID from dict_place);
delete from dict_place_version where firstID not in(select placeID from dict_place);
delete from dict_app_config where firstID not in(select placeID from dict_place);
delete from dict_config where productId != 0 and productId not in(select placeID from dict_place);