-- 经纬度之间的距离

SELECT *,  ROUND(
        6378.138 * 2 * ASIN(
            SQRT(
                POW(
                    SIN(
                        (
                            '32.039192' * PI() / 180 - lat * PI() / 180
                        ) / 2
                    ),
                    2
                ) + COS(40.0497810000 * PI() / 180) * COS(lat * PI() / 180) * POW(
                    SIN(
                        (
                            '118.819705' * PI() / 180 - lng * PI() / 180
                        ) / 2
                    ),
                    2
                )
            )
        ) * 1000
    ) AS distance
FROM
    t_sport_room
ORDER BY
    distance ASC