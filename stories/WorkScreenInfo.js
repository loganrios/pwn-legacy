import React from "react";
import { Box, Typography, Button, ButtonBase, Chip } from "@mui/material";
import {
  styled,
  createTheme,
  responsiveFontSizes,
  ThemeProvider,
} from "@mui/material/styles";
import EditIcon from "@mui/icons-material/Edit";
import LaunchIcon from "@mui/icons-material/Launch";

let theme = createTheme();
theme = responsiveFontSizes(theme);

const Img = styled("img")({
  margin: "auto",
  display: "block",
  maxWidth: "100%",
  maxHeight: "100%",
});

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 400,
  bgcolor: "background.paper",
  border: "2px solid #000",
  boxShadow: 24,

  px: 4,
  pb: 3,
};

const WorkScreenInfo = ({
  workTitle,
  blurb,
  image,
  onEdit,
  onGoToPage,
  onEditCover,
  author,
  totalViews,
  averageViews,
  followers,
  publicRatings,
  pages,
  genres,
  tags,
  warnings,
  avgPostTime,
  avgChapterLength,
}) => {
  return (
    <div>
      <ThemeProvider theme={theme}>
        <Box
          sx={{
            display: "grid",
            borderRadius: 2,
            boxShadow: 3,
            gridTemplateColumns: "auto",
            gap: 0,
            p: { xs: 0, sm: 2 },
            alignItems: "center",
            justifyContent: "center",
            gridTemplateRows: "auto",
            gridTemplateAreas: {
              xs: `"title title title title"
                   "cover cover cover cover"
                   "tags  tags  tags  tags"
                   "stats stats stats stats"
                   "blurb blurb blurb blurb"
                   "blurb blurb blurb blurb"
                   "avg   avg   avg   avg"`,
              sm: `"cover cover title title title title"
                   "cover cover tags  tags  tags  tags"
                   "cover cover blurb blurb stats stats"
                   "cover cover blurb blurb stats stats"
                   "avg   avg   avg   avg   avg   avg"`,
            },
          }}
        >
          <Box
            sx={{
              gridArea: "title",
              display: "inline",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <Typography variant="h4" align="center" gutterBottom sx={{ m: 0 }}>
              {workTitle}
            </Typography>
            <Typography variant="h5" align="center" gutterBottom>
              By: {author}
            </Typography>
          </Box>
          <Box
            sx={{
              gridArea: "tags",
              display: "flex",
              justifyContent: "center",
              mx: "auto",
              flexWrap: "wrap",
            }}
          >
            {genres?.map((item) => (
              <Chip
                label={item.label}
                onClick={item.onTagFilter}
                variant="outlined"
                color="primary"
              />
            ))}
            {tags?.map((item) => (
              <Chip
                label={item.label}
                onClick={item.onTagFilter}
                variant="outlined"
                color="success"
              />
            ))}
            {warnings?.map((item) => (
              <Chip
                label={item.label}
                onClick={item.onTagFilter}
                variant="outlined"
                color="error"
              />
            ))}
          </Box>
          <Box
            sx={{
              gridArea: "stats",
              display: "inline",
              justifyContent: "center",
              mx: "auto",
            }}
          >
            <Typography variant="body1" align="center">
              Stats:
            </Typography>
            <Typography variant="body1" align="center">
              Total Views: {totalViews}
            </Typography>
            <Typography variant="body1" align="center">
              Average Views: {averageViews}
            </Typography>
            <Typography variant="body1" align="center">
              Followers: {followers}
            </Typography>
            <Typography variant="body1" align="center">
              Public Ratings: {publicRatings}
            </Typography>
            <Typography variant="body1" align="center">
              Pages: {pages}
            </Typography>
          </Box>
          <Box
            sx={{
              gridArea: "cover",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <ButtonBase onClick={onEditCover} sx={{ width: 90, height: 144 }}>
              <Img alt="cover" src={image} />
            </ButtonBase>
          </Box>
          <Box
            sx={{
              gridArea: "blurb",
              display: "flex",
              alignItems: "flex-start",
              ml: 2,
              mr: 2,
            }}
          >
            <Typography variant="body1">{blurb}</Typography>
          </Box>
          <Box
            sx={{
              gridArea: "avg",
              display: "inline",
              alignItems: "center",
              justifyContent: "center",
              mt: 3,
            }}
          >
            <Typography variat="body1" align="center">
              Average Post Time: {avgPostTime}
            </Typography>
            <Typography variat="body1" align="center">
              Average Chapter Length: {avgChapterLength}
            </Typography>
          </Box>
        </Box>
      </ThemeProvider>
    </div>
  );
};

export default WorkScreenInfo;
