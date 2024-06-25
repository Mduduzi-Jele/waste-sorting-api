First, I utilized the command line runner interface to add some initial data when the application starts. I only did this for my own testing since I'm not using file storage with the H2 database.
The application consists of three entities: Category (types of materials, e.g., metal, plastic, etc.), Guideline (instructions on how to recycle a particular type of material),
and Tip (any additional information that might be helpful). Category has a one-to-many relationship with both Guideline and Tip.
My reasoning was that the disposal guidelines retrieval and recycling tips we will be providing to users will depend on the type of material (the category to which the material belongs).
