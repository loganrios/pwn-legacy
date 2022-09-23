import React from "react";
import {
  Box,
  Typography,
  Button,
  ButtonBase,
  Link,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Modal,
  TextField,
  InputAdornment,
} from "@mui/material";
import {
  styled,
  createTheme,
  responsiveFontSizes,
  ThemeProvider,
} from "@mui/material/styles";

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
  pt: 2,
  px: 4,
  pb: 3,
};

function SponsorModal({ sponsorText, onSponsorSubmit }) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => {
    setOpen(true);
  };
  const handleClose = () => {
    setOpen(false);
  };
  const [sponsorAmount, setSponsorAmount] = React.useState();

  return (
    <>
      <Button
        variant="contained"
        aria-label="sponsor"
        onClick={handleOpen}
        disabled
      >
        Sponsor
      </Button>
      <Modal open={open} onClose={handleClose}>
        <Box
          sx={{
            ...style,
            width: 400,
            display: "flex",
            flexDirection: "column",
            gap: 1,
          }}
        >
          <h2 id="sponsorship-modal">Sponsor</h2>
          <Typography variant="body1">{sponsorText}</Typography>
          <TextField
            label="Sponsor Amount"
            value={sponsorAmount}
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">$</InputAdornment>
              ),
            }}
            onChange={(evt) =>
              setSponsorAmount(evt.target.value.replace(/\D/g, ""))
            }
          />
          <Button
            variant="contained"
            onClick={() => {
              onSponsorSubmit(sponsorAmount);
              handleClose();
            }}
          >
            Submit
          </Button>
          <Button variant="outlined" onClick={handleClose}>
            Cancel
          </Button>
        </Box>
      </Modal>
    </>
  );
}

const AuthorBio = ({
  username,
  bio,
  wordCount,
  reviewsCount,
  ratingsCount,
  image,
  isOwner,
  onFollow,
  readingList,
  link,
  desc,
  onSubmit,
  onEditAvatar,
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
            gap: 1,
            p: { xs: 0, sm: 2 },
            gridTemplateRows: "auto",
            gridTemplateAreas: {
              xs: `"username username username username"
                   "follow follow sponsor sponsor"
                   "stats  stats  bio    bio"
                   "stats  stats  bio    bio"
                   "  .      .    links  links"`,
              sm: `"username username username follow sponsor"
                   "bio      bio      bio      bio    bio"
                   "bio      bio      bio      bio    bio"
                   "stats    stats    links    links  links"`,
            },
          }}
        >
          <Box
            sx={{
              gridArea: "username",
              display: "inline-flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <Typography variant="h4">{username}</Typography>
          </Box>
          <Box
            sx={{
              gridArea: "follow",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            <Button variant="contained" aria-label="follow" onClick={onFollow}>
              Follow
            </Button>
          </Box>
          <Box
            sx={{
              gridArea: "sponsor",
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
            }}
          >
            {isOwner ? (
              <Button variant="contained" disabled>
                Sponsor
              </Button>
            ) : (
              <SponsorModal sponsorText={desc} onSponsorSubmit={onSubmit} />
            )}
          </Box>
          <Box
            sx={{
              gridArea: "bio",
              display: "flex",
              alignItems: "flex-start",
            }}
          >
            <Typography variant="body1">{bio}</Typography>
          </Box>
          <Box
            sx={{
              gridArea: "links",
              display: "flex",
              alignItems: "center",
              flexDirection: "column",
            }}
          >
            <Link underline="hover" color="inherit" href={readingList}>
              Reading List
            </Link>
            {link.map((item) => (
              <Box sx={{ display: "flex" }}>
                <Link underline="hover" color="inherit" href={item.url}>
                  {item.label}
                </Link>
              </Box>
            ))}
          </Box>
          <Box
            sx={{
              gridArea: "stats",
              display: "flex",
              flexDirection: "column",
              lineHeight: 1,
            }}
          >
            <Typography variant="body1">Word Count: {wordCount}</Typography>
            <Typography variant="body1">
              Public Reviews: {reviewsCount}
            </Typography>
            <Typography variant="body1">
              Public Ratings: {ratingsCount}
            </Typography>
          </Box>
        </Box>
      </ThemeProvider>
    </div>
  );
};

export default AuthorBio;
