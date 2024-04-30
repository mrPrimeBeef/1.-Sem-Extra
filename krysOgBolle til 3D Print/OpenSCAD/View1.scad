difference()
{
    difference()
    {
        difference()
        {
            linear_extrude(height = 10.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
            {
                scale([122.0, 122.0])
                {
                    M8();
                }
            }
            union()
            {
                union()
                {
                    translate([-39.0, 39.0, 6.0])
                    {
                        linear_extrude(height = 20.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
                        {
                            scale([34.0, 34.0])
                            {
                                M8();
                            }
                        }
                    }
                    translate([39.0, 0.0, 0.0])
                    {
                        translate([-39.0, 39.0, 6.0])
                        {
                            linear_extrude(height = 20.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
                            {
                                scale([34.0, 34.0])
                                {
                                    M8();
                                }
                            }
                        }
                    }
                }
                translate([39.0, 0.0, 0.0])
                {
                    translate([39.0, 0.0, 0.0])
                    {
                        translate([-39.0, 39.0, 6.0])
                        {
                            linear_extrude(height = 20.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
                            {
                                scale([34.0, 34.0])
                                {
                                    M8();
                                }
                            }
                        }
                    }
                }
            }
        }
        translate([0.0, -39.0, 0.0])
        {
            union()
            {
                union()
                {
                    translate([-39.0, 39.0, 6.0])
                    {
                        linear_extrude(height = 20.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
                        {
                            scale([34.0, 34.0])
                            {
                                M8();
                            }
                        }
                    }
                    translate([39.0, 0.0, 0.0])
                    {
                        translate([-39.0, 39.0, 6.0])
                        {
                            linear_extrude(height = 20.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
                            {
                                scale([34.0, 34.0])
                                {
                                    M8();
                                }
                            }
                        }
                    }
                }
                translate([39.0, 0.0, 0.0])
                {
                    translate([39.0, 0.0, 0.0])
                    {
                        translate([-39.0, 39.0, 6.0])
                        {
                            linear_extrude(height = 20.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
                            {
                                scale([34.0, 34.0])
                                {
                                    M8();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    translate([0.0, -78.0, 0.0])
    {
        union()
        {
            union()
            {
                translate([-39.0, 39.0, 6.0])
                {
                    linear_extrude(height = 20.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
                    {
                        scale([34.0, 34.0])
                        {
                            M8();
                        }
                    }
                }
                translate([39.0, 0.0, 0.0])
                {
                    translate([-39.0, 39.0, 6.0])
                    {
                        linear_extrude(height = 20.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
                        {
                            scale([34.0, 34.0])
                            {
                                M8();
                            }
                        }
                    }
                }
            }
            translate([39.0, 0.0, 0.0])
            {
                translate([39.0, 0.0, 0.0])
                {
                    translate([-39.0, 39.0, 6.0])
                    {
                        linear_extrude(height = 20.0, twist = 0.0, scale = 1.0, slices = 1, center = false)
                        {
                            scale([34.0, 34.0])
                            {
                                M8();
                            }
                        }
                    }
                }
            }
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
