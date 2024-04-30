linear_extrude(height = 4.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
{
    intersection()
    {
        rotate(45.0)
        {
            union()
            {
                scale([42.42640687119285, 5.0])
                {
                    M8();
                }
                rotate(90.0)
                {
                    scale([42.42640687119285, 5.0])
                    {
                        M8();
                    }
                }
            }
        }
        scale([30.0, 30.0])
        {
            M8();
        }
    }
}

module M8()
{
    polygon
    (
        points =
        [
            [-0.5, -0.5], 
            [0.5, -0.5], 
            [0.5, 0.5], 
            [-0.5, 0.5]
        ],
        paths =
        [
            [0, 1, 2, 3]
        ]
    );
}
